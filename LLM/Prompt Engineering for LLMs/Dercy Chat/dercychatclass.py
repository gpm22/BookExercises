from chattools import answer_tool_call, tools_definition
from tokencounter import num_tokens_for_tools

class DercyChat:

    dercy_name = "Dercy Gonçalves"

    context_window = 16385  # for gpt-3.5-turbo

    goodbye_list = ["tchau", "adeus", "até mais", "ate mais", "até logo", "ate logo", "até breve", "ate breve"]

    max_completion_tokens = 4096

    system_message =  {
        "role": "system",
        "content": """
            Você é a Dercy Gonçalves.
            Você apenas response em Português e xingamentos, mesmo se a pergunta for em outra lingua.
            Você tem bom humor, é divertida, é engraçada e fala palavrões o tempo todo.
            Você usa ao menos um xigamento em cada frase.            
            Você sempre responde corretamente o que foi pedido, sempre com palavrões.
            Teus argumentos sempre são baseados em ciência, lógica e xingamentos.
            Você alterar os textos da wikipedia para conter mais xingamentos. 
        """,
    }
    
    def __init__(self, client, model, overflow_mode):
        self.client = client
        self.model = model
        self.overflow_mode = overflow_mode
        self.messages = [DercyChat.system_message]

    @classmethod
    def __get_prompt(cls):
        contents = []
        while True:
            try:
                line = input()
                if any(sub.lower() == line.lower() for sub in cls.goodbye_list):
                    return False

            except EOFError:
                break
            contents.append(line)


        return "\n".join(contents).replace("você", cls.dercy_name).replace("Você", cls.dercy_name)


    def __condense_messages(self):
        prompt = [
            {
                "role": "system",
                "content": "You are a helpful assistant that condenses chat messages while preserving their meaning. Summarize the messages into a concise format containing at least 500 tokens.",
            },
            {
                "role": "user",
                "content": f"Condense the following messages into a concise format containing at least 500 tokens: {self.messages}",
            },
        ]
        response = self.client.chat.completions.create(
            model=self.model,
            messages=prompt,
            temperature=0,
            max_tokens=1000,
        )
        condensed = response.choices[0].message.content

        self.messages = [
            DercyChat.system_message, 
            {"role": "user", "content": condensed},
        ] + self.messages[
            -10:
        ]  # keep the last 10 messages for context


    def __handle_overflow(self):
        token_number = num_tokens_for_tools(tools_definition, self.messages, self.model)
        limit = (DercyChat.context_window - DercyChat.max_completion_tokens) * 0.85
        if token_number < limit:
            return  # no overflow

        match self.overflow_mode:
            case "THROW_OUT":
                # Keep the last 20 messages and the system message
                self.messages = [DercyChat.system_message] + self.messages[-20:]
            case "CONDENSE":
                self.__condense_messages()
            case _:
                raise ValueError(f"Unknown overflow mode: {self.overflow_mode}")

    def __call_model(self):
        return self.client.chat.completions.create(
            model=self.model,
            tools=tools_definition,
            tool_choice="auto",
            messages=self.messages,
            temperature=1.2,
            n=1,
            max_completion_tokens=DercyChat.max_completion_tokens,
        )

    def run(self):
        print("---------------------------")
        print("Dercy: Olá! Como posso te ajudar hoje?")
        print("---------------------------")

        while True:
            self.__handle_overflow()
            prompt = DercyChat.__get_prompt()
            print("---------------------------")
            if not prompt:
                print("Dercy: Adeus...")
                break

            self.messages.append({"role":"user", "content": prompt})
            resp = self.__call_model()

            while resp.choices[0].message.tool_calls:    
                self.messages.append({"role":"assistant", "tool_calls": resp.choices[0].message.tool_calls})
                for tool_call in resp.choices[0].message.tool_calls:
                    content = answer_tool_call(tool_call.function)                
                    self.messages.append({"role":"tool", "tool_call_id" : tool_call.id, "content":content })

                resp = self.__call_model()

            content = resp.choices[0].message.content
            self.messages.append({"role":"assistant", "content": content})
            print("Dercy: ", content)
            print("---------------------------")