from openai import OpenAI
from dercychatclass import DercyChat
import argparse

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
        help="Mode to handle context overflow. Options:\n THROW_OUT -- keep last 20 messages,\nCONDENSE -- condense the messages in 500-1000 tokens and keep last 10 messages.",
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
    client = OpenAI()
    dercy_chat = DercyChat(
        client=client,
        model=args.model,
        overflow_mode=args.overflow_mode,
    )
    dercy_chat.run()
