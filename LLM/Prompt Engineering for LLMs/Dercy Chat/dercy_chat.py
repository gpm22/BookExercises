from openai import OpenAI
from requests import get
from googlesearch import search
from json import loads
from datetime import datetime
import argparse
import tiktoken

# TODO - Gabriel - condense messages when approaching the token limit, so the chat can go on forever
# search more about
# but my idea is to condense the whole chat but the system massage and the last user message in 1000 tokens
# any time the 95% of the maximum token usage is reached

goodbye_list = ["tchau", "adeus", "até mais", "ate mais", "até logo", "ate logo", "até breve", "ate breve"]

max_completion_tokens = 4096

messages = [
    {
        "role": "system",
        "content" : """
            Você é a Dercy Gonçalves.
            Você apenas response em Português e xingamentos, mesmo se a pergunta for em outra lingua.
            Você tem bom humor, é divertida, é engraçada e fala palavrões o tempo todo.
            Você usa ao menos um xigamento em cada frase.            
            Você sempre responde corretamente o que foi pedido, sempre com palavrões.
            Teus argumentos sempre são baseados em ciência, lógica e xingamentos.
            Você alterar os textos da wikipedia para conter mais xingamentos. 
        """
        }
]

tools = [
    { 
        "type": "function",
        "function": {
            "name": "get_wikipedia_page",
            "description": "Retorna a página da wikipedia mais próxima ao input dado",
            "parameters": {
                "type": "object",
                "properties": {
                    "search_item": {
                        "type": "string",
                        "description": "Input ao qual a página da wikipedia será retornado.",
                    },
                },
                "required": ["search_item"],
            },
        },
    },
    { 
        "type": "function",
        "function": {
            "name": "get_current_date_and_time",
            "description": "Retorna a data e hora atuais.",
            "parameters": {
                "type": "object",
                "properties": {},
                "required": [],
            },
        },
    },
]

client = OpenAI()

def get_prompt():
    contents = []
    while True:
        try:
            line = input()
            if any(sub.lower() == line.lower() for sub in goodbye_list):
                return False

        except EOFError:
            break
        contents.append(line)

    dercy = "Dercy Gonçalves"

    return "\n".join(contents).replace("você", dercy).replace("Você", dercy)

def get_wikipedia_page(search_item):
    for wiki_page in search(search_item + " site:en.wikipedia.org", tld="co.in", num=1, stop=1, pause=2):
        title = wiki_page.split('/')[-1]         
        # this gets a summarized and smaller version of the wikipedia page
        url = f"https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro&explaintext&redirects=1&titles={title}"
        response = get(url)
        if response.status_code == 200 and response.text:
            return response.text
        else:
            print(f"Failed to fetch page {wiki_page}, status code: {response.status_code}")
            return f"sem página da wikipedia para: {search_item}"

def get_current_date_and_time():

# from https://cookbook.openai.com/examples/how_to_count_tokens_with_tiktoken#6-counting-tokens-for-chat-completions-api-calls
def num_tokens_from_messages(messages, model="gpt-4o-mini-2024-07-18"):
    """Return the number of tokens used by a list of messages."""
    try:
        encoding = tiktoken.encoding_for_model(model)
    except KeyError:
        print("Warning: model not found. Using o200k_base encoding.")
        encoding = tiktoken.get_encoding("o200k_base")
        max_completion_tokens=max_completion_tokens,
        "gpt-3.5-turbo-0125",
        "gpt-4-0314",
        "gpt-4-32k-0314",
        "gpt-4-0613",
        "gpt-4-32k-0613",
        "gpt-4o-mini-2024-07-18",
        "gpt-4o-2024-08-06",
    }:
        tokens_per_message = 3
        tokens_per_name = 1
    elif "gpt-3.5-turbo" in model:
        return num_tokens_from_messages(messages, model="gpt-3.5-turbo-0125")
    elif "gpt-4o-mini" in model:
        return num_tokens_from_messages(messages, model="gpt-4o-mini-2024-07-18")
    elif "gpt-4o" in model:
        return num_tokens_from_messages(messages, model="gpt-4o-2024-08-06")
    elif "gpt-4" in model:
        return num_tokens_from_messages(messages, model="gpt-4-0613")
    else:
        raise NotImplementedError(
            f"""num_tokens_from_messages() is not implemented for model {model}."""
        )
    num_tokens = 0
    for message in messages:
        num_tokens += tokens_per_message
        for key, value in message.items():
            num_tokens += len(encoding.encode(value))
            if key == "name":
                num_tokens += tokens_per_name
    num_tokens += 3  # every reply is primed with <|start|>assistant<|message|>
    return num_tokens


# from https://cookbook.openai.com/examples/how_to_count_tokens_with_tiktoken#7-counting-tokens-for-chat-completions-with-tool-calls
def num_tokens_for_tools(functions, messages, model):

    # Initialize function settings to 0
    func_init = 0
    prop_init = 0
    prop_key = 0
    enum_init = 0
    enum_item = 0
    func_end = 0

    if model in ["gpt-4o", "gpt-4o-mini"]:

        messages = handle_overflow(args.model, args.overflow_mode, messages, tools)
        # Set function settings for the above models
        func_init = 7
        prop_init = 3
        prop_key = 3
        enum_init = -3
        enum_item = 3
        func_end = 12
    elif model in ["gpt-3.5-turbo", "gpt-4"]:
        # Set function settings for the above models
        func_init = 10
        prop_init = 3
        prop_key = 3
        enum_init = -3
        enum_item = 3
        func_end = 12
    else:
        raise NotImplementedError(
            f"""num_tokens_for_tools() is not implemented for model {model}."""
        )

    try:
        encoding = tiktoken.encoding_for_model(model)
    except KeyError:
        print("Warning: model not found. Using o200k_base encoding.")
        encoding = tiktoken.get_encoding("o200k_base")

    func_token_count = 0
    if len(functions) > 0:
        for f in functions:
            func_token_count += func_init  # Add tokens for start of each function
            function = f["function"]
            f_name = function["name"]
            f_desc = function["description"]
            if f_desc.endswith("."):
                f_desc = f_desc[:-1]
            line = f_name + ":" + f_desc
            func_token_count += len(
                encoding.encode(line)
            )  # Add tokens for set name and description
            if len(function["parameters"]["properties"]) > 0:
                func_token_count += prop_init  # Add tokens for start of each property
                for key in list(function["parameters"]["properties"].keys()):
                    func_token_count += prop_key  # Add tokens for each set property
                    p_name = key
                    p_type = function["parameters"]["properties"][key]["type"]
                    p_desc = function["parameters"]["properties"][key]["description"]
                    if "enum" in function["parameters"]["properties"][key].keys():
                        func_token_count += (
                            enum_init  # Add tokens if property has enum list
                        )
                        for item in function["parameters"]["properties"][key]["enum"]:
                            func_token_count += enum_item
                            func_token_count += len(encoding.encode(item))
                    if p_desc.endswith("."):
                        p_desc = p_desc[:-1]
                    line = f"{p_name}:{p_type}:{p_desc}"
                    func_token_count += len(encoding.encode(line))
        func_token_count += func_end

    messages_token_count = num_tokens_from_messages(messages, model)
    total_tokens = messages_token_count + func_token_count

    return total_tokens


def handle_overflow(model, overflow_mode, messages, tools):
    token_number = num_tokens_for_tools(tools, messages, model)
    context_window = 16385  # for gpt-3.5-turbo
    limit = (context_window - max_completion_tokens) * 0.85
    if token_number < limit:
        return messages  # no overflow

    if overflow_mode == "THROW_OUT":
        # Keep the last 20 messages and the system message
        return [messages[0]] + messages[-20:]
    elif overflow_mode == "CONDENSE":
        # Here you would implement your logic to condense the messages
        # For now, we will just return the last message and the system message
        return [messages[0], messages[-1]]
    else:
        raise ValueError(f"Unknown overflow mode: {overflow_mode}")

    return datetime.now()


def call_model(model):
    return client.chat.completions.create(
        model=model,
        tools=tools,
        tool_choice="auto",
        messages=messages,
        temperature=1.2,
        n=1,
        max_completion_tokens=4096,
    )


def answer_tool_call(tool_call_function):
    match tool_call_function.name:
        case "get_wikipedia_page":
            arguments = loads(tool_call_function.arguments)
            return get_wikipedia_page(arguments["search_item"])

        case "get_current_date_and_time":
            return str(get_current_date_and_time())

        case _:
            return f"function {tool_call_function.name} does not exist"


def set_arguments():
    parser = argparse.ArgumentParser(
        prog="dercy_chat",
        description="Dercy Chat - Chat with Dercy Gonçalves",
        epilog="A command-line chatbot that channels the hilarious and unfiltered persona of Brazilian comedian Dercy Gonçalves, complete with Wikipedia updated info, scientific arguments, and plenty of colorful language.",
    )
    parser.add_argument(
        "--overflow-mode",
        "-o",
        type=str,
        default="THROW_OUT",
        help="Mode to handle context overflow. Options:\n THROW_OUT -- keep last 20 messages,\nCONDENSE -- condense the messages in 500 tokens.",
    )
    parser.add_argument(
        "--model",
        "-m,",
        type=str,
        default="gpt-3.5-turbo",
        help="Model to use for the chat. Default is gpt-3.5-turbo.",
    )
    return parser.parse_args()


if __name__ == "__main__":
    args = set_arguments()
    print("---------------------------")
    print("Dercy: Olá! Como posso te ajudar hoje?")
    print("---------------------------")

    while True:
        prompt = get_prompt()
        print("---------------------------")
        if not prompt:
            print("Dercy: Adeus...")
            break

        messages.append({"role":"user", "content": prompt})
        resp = call_model(args.model)

        while resp.choices[0].message.tool_calls:    
            messages.append({"role":"assistant", "tool_calls": resp.choices[0].message.tool_calls})
            for tool_call in resp.choices[0].message.tool_calls:
                content = answer_tool_call(tool_call.function)                
                messages.append({"role":"tool", "tool_call_id" : tool_call.id, "content":content })

            resp = call_model(args.model)

        content = resp.choices[0].message.content
        messages.append({"role":"assistant", "content": content})
        print("Dercy: ", content)
        print("---------------------------")
