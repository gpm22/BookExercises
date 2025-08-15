# Dercy Chat - CLI Assistant

A command-line chatbot that channels the hilarious and unfiltered persona of Brazilian comedian Dercy Gonçalves, complete with Wikipedia updated info, scientific arguments, and plenty of colorful language.

## Features

- Interactive chat interface with Dercy Gonçalves' unique personality
- Scientific and logical arguments wrapped in hilarious profanity
- Context-aware conversation (maintains chat history)
- Access to Wikipedia pages, so questions with information outside its training set can be answered.

## Prerequisites

- Python 3.x
- OpenAI API key

## Installation

1. First, install the required packages:

   ```bash
   pip install openai
   pip install beautifulsoup4
   pip install google
   ```

2. Set your OpenAI API key as an environment variable:

   ```bash
   export OPENAI_API_KEY='your-api-key-here'
   ```

   * (For Windows, use `set` instead of `export`)

## Usage

1. Run the script:

   ```bash
   python dercy_chat.py
   ```

2. The program will greet you and wait for your input. Type your message and send an EOF signal (Ctrl+D on Unix, Ctrl+Z+Enter on Windows).

3. To end the conversation, type any of the following words:

   * "tchau"
   * "adeus"
   * "até mais", "ate mais"
   * "até logo" ou "ate logo"
   * "até breve" ou "ate breve"

## How It Works

- The script replaces all instances of "você/Você" (you in Portuguese) with "Dercy Gonçalves" in your input
- Messages maintain context through the conversation history
- Uses GPT-3.5-turbo with a higher temperature (1.2) for more creative responses
- Each response is guaranteed to contain at least one profanity (as per the system prompt)

## Customization

You can modify the system prompt in the `messages` list to adjust Dercy's personality traits or change the model.

## Example Session

```bash
---------------------------
Dercy: Olá! Como posso te ajudar hoje?
---------------------------
oi Dercy, qual é a capital da França?
---------------------------
Dercy:  Ah, porra, Paris, né, caralho! Todo mundo conhece essa porra, porra! Aquela cidade cheia de croissant e vinho, porra! E olha que eu nunca fui lá, mas soube disso pelo menos, cacete!
---------------------------
tchau
---------------------------
Dercy: Adeus...
```

## Notes

- The answers will be only in Portuguese
- Keep your API key secure and don't commit it to version control
- The script maintains conversation history in memory, but doesn't persist between sessions
- The model does not use the whole Wikipedia page, but just a summarized version of it.
- Be mindful of OpenAI's API usage costs