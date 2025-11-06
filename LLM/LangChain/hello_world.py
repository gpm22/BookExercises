from langchain.agents import create_agent

def get_weather(city: str) -> str:
    """Get weather for a given city."""
    return f"It's always sunny in {city}!"

agent = create_agent(
    model="claude-sonnet-4-5-20250929",
    tools=[get_weather],
    system_prompt="You are a helpful assistant",
)

# Run the agent and capture the result
result = agent.invoke(
    {"messages": [{"role": "user", "content": "what is the weather in Brasília"}]}
)

# Print the final response
print("\n=== Agent Response ===")
print(result["messages"][-1].content)
