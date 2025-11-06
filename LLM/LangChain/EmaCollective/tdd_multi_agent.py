#!/usr/bin/env python3
"""
TDD Multi-Agent System using LangChain
Implements Test-Driven Development with multiple AI agents (Claude, Gemini, GPT)
"""

import os
import sys
from typing import List, Dict, Any, Tuple
from dataclasses import dataclass
from enum import Enum

from langchain_anthropic import ChatAnthropic
from langchain_google_genai import ChatGoogleGenerativeAI
from langchain_openai import ChatOpenAI
from langchain_core.messages import HumanMessage, SystemMessage
from langchain_core.prompts import ChatPromptTemplate


class AgentType(Enum):
    """Enum for different agent types"""
    CLAUDE = "claude"
    GEMINI = "gemini"
    GPT = "gpt"


@dataclass
class AgentResponse:
    """Response from an agent"""
    agent_type: AgentType
    content: str
    success: bool
    reasoning: str = ""


class AgentOrchestrator:
    """Orchestrates multiple AI agents for TDD workflow"""
    
    def __init__(self):
        """Initialize all AI agents"""
        self.agents = self._initialize_agents()
        self.max_iterations = 5  # Maximum retry iterations
        
    def _initialize_agents(self) -> Dict[AgentType, Any]:
        """Initialize Claude, Gemini, and GPT agents"""
        agents = {}
        
        # Claude
        claude_api_key = os.getenv("ANTHROPIC_API_KEY")
        if claude_api_key:
            agents[AgentType.CLAUDE] = ChatAnthropic(
                model="claude-sonnet-4-20250514",
                anthropic_api_key=claude_api_key,
                temperature=0.7
            )
        
        # Gemini
        gemini_api_key = os.getenv("GOOGLE_API_KEY")
        if gemini_api_key:
            agents[AgentType.GEMINI] = ChatGoogleGenerativeAI(
                model="gemini-2.0-flash-exp",
                google_api_key=gemini_api_key,
                temperature=0.7
            )
        
        # GPT
        openai_api_key = os.getenv("OPENAI_API_KEY")
        if openai_api_key:
            agents[AgentType.GPT] = ChatOpenAI(
                model="gpt-4o",
                openai_api_key=openai_api_key,
                temperature=0.7
            )
        
        if not agents:
            raise ValueError(
                "No API keys found! Please set at least one of: "
                "ANTHROPIC_API_KEY, GOOGLE_API_KEY, OPENAI_API_KEY"
            )
        
        return agents
    
    def _invoke_agent(self, agent_type: AgentType, messages: List) -> str:
        """Invoke a specific agent with messages"""
        if agent_type not in self.agents:
            raise ValueError(f"Agent {agent_type.value} not initialized. Check API key.")
        
        response = self.agents[agent_type].invoke(messages)
        return response.content
    
    def create_tests(self, requirement: str, feedback: str = "") -> List[AgentResponse]:
        """Step 1: Group of agents create tests"""
        print("\n" + "="*80)
        print("STEP 1: Creating Tests")
        print("="*80)
        
        system_prompt = """You are an expert test developer. Create comprehensive unit tests 
for the given requirement using pytest. Include edge cases and validation tests.

Return your response in the following format:
```python
'''
Example Generated Tests
This file shows what the generated_tests.py might look like after running the system
'''

import pytest


def test_is_prime_basic_primes():
    '''Test basic prime numbers'''
    assert is_prime(2) == True
    assert is_prime(3) == True
    assert is_prime(5) == True
    assert is_prime(7) == True
    assert is_prime(11) == True
    assert is_prime(13) == True


def test_is_prime_basic_composites():
    '''Test basic composite numbers'''
    assert is_prime(4) == False
    assert is_prime(6) == False
    assert is_prime(8) == False
    assert is_prime(9) == False
    assert is_prime(10) == False


def test_is_prime_edge_cases():
    '''Test edge cases'''
    assert is_prime(0) == False
    assert is_prime(1) == False
    assert is_prime(2) == True  # Smallest prime


def test_is_prime_negative_numbers():
    '''Test negative numbers'''
    assert is_prime(-1) == False
    assert is_prime(-5) == False
    assert is_prime(-10) == False


def test_is_prime_large_primes():
    '''Test large prime numbers'''
    assert is_prime(97) == True
    assert is_prime(101) == True
    assert is_prime(103) == True


def test_is_prime_large_composites():
    '''Test large composite numbers'''
    assert is_prime(100) == False
    assert is_prime(102) == False
    assert is_prime(104) == False


def test_is_prime_type_validation():
    '''Test type validation'''
    with pytest.raises(TypeError):
        is_prime('5')
    
    with pytest.raises(TypeError):
        is_prime(5.5)
    
    with pytest.raises(TypeError):
        is_prime(None)


def test_is_prime_performance():
    '''Test performance with larger numbers'''
    # Should handle reasonably large numbers efficiently
    assert is_prime(7919) == True
    assert is_prime(7920) == False
```

Make tests clear, comprehensive, and following best practices."""
        
        user_prompt = f"""Requirement: {requirement}

{f'Previous feedback to address: {feedback}' if feedback else ''}

Create comprehensive pytest unit tests for this requirement."""
        
        responses = []
        for agent_type in self.agents.keys():
            print(f"\n{agent_type.value.upper()} is creating tests...")
            try:
                messages = [
                    SystemMessage(content=system_prompt),
                    HumanMessage(content=user_prompt)
                ]
                content = self._invoke_agent(agent_type, messages)
                responses.append(AgentResponse(
                    agent_type=agent_type,
                    content=content,
                    success=True
                ))
                print(f"✓ {agent_type.value.upper()} completed")
                # todo - remove
                print("GENERATED RESPONSE:")
                print(content)
                print("==============================================")
            except Exception as e:
                print(f"✗ {agent_type.value.upper()} failed: {str(e)}")
                responses.append(AgentResponse(
                    agent_type=agent_type,
                    content="",
                    success=False,
                    reasoning=str(e)
                ))
        
        return responses
    
    def validate_tests(self, requirement: str, test_responses: List[AgentResponse]) -> Tuple[bool, str]:
        """Step 2: Validate the tests created by the first group"""
        print("\n" + "="*80)
        print("STEP 2: Validating Tests")
        print("="*80)
        
        # Combine all test responses
        all_tests = "\n\n".join([
            f"=== Tests from {r.agent_type.value.upper()} ===\n{r.content}"
            for r in test_responses if r.success
        ])
        
        system_prompt = """You are an expert test validator. Evaluate the provided tests for:
1. Completeness - Do they cover the requirement adequately?
2. Quality - Are they well-structured and follow best practices?
3. Correctness - Are they syntactically correct and runnable?
4. Edge cases - Do they test boundary conditions?

Respond in the following format:
VALID: [YES/NO]
REASONING: [Your detailed reasoning]
FEEDBACK: [Specific feedback for improvement if invalid]"""
        
        user_prompt = f"""Requirement: {requirement}

Tests to validate:
{all_tests}

Validate these tests."""
        
        validation_results = []
        for agent_type in self.agents.keys():
            print(f"\n{agent_type.value.upper()} is validating tests...")
            try:
                messages = [
                    SystemMessage(content=system_prompt),
                    HumanMessage(content=user_prompt)
                ]
                content = self._invoke_agent(agent_type, messages)
                validation_results.append(content)
                print(f"✓ {agent_type.value.upper()} completed validation")
                print("GENERATED RESPONSE:")
                print(content)
                print("==============================================")
            except Exception as e:
                print(f"✗ {agent_type.value.upper()} validation failed: {str(e)}")
        
        # Analyze validation results (majority vote)
        valid_count = sum(1 for result in validation_results if "VALID: YES" in result.upper())
        is_valid = valid_count >= len(validation_results) / 2
        
        # Combine feedback
        feedback = "\n\n".join([f"=== Validator {i+1} ===\n{result}" 
                                for i, result in enumerate(validation_results)])
        
        print(f"\nValidation Result: {'✓ VALID' if is_valid else '✗ INVALID'}")
        print(f"Valid votes: {valid_count}/{len(validation_results)}")
        
        return is_valid, feedback
    
    def create_code(self, requirement: str, tests: str, feedback: str = "") -> List[AgentResponse]:
        """Step 4: Group of agents create code based on tests"""
        print("\n" + "="*80)
        print("STEP 4: Creating Code")
        print("="*80)
        
        system_prompt = """You are an expert software developer. Create production-quality code 
that passes all the provided tests. Follow best practices, include docstrings, and ensure 
the code is clean and maintainable.

Return your response in the following format:
```python
def is_prime(n):
    '''
    Check if a number is prime.
    
    A prime number is a natural number greater than 1 that has no positive
    divisors other than 1 and itself.
    
    Args:
        n (int): The number to check for primality
        
    Returns:
        bool: True if n is prime, False otherwise
        
    Raises:
        TypeError: If n is not an integer
        
    Examples:
        >>> is_prime(2)
        True
        >>> is_prime(4)
        False
        >>> is_prime(17)
        True
    '''
    # Type validation
    if not isinstance(n, int):
        raise TypeError(f'Expected integer, got {type(n).__name__}')
    
    # Handle edge cases
    if n < 2:
        return False
    
    # 2 is the only even prime number
    if n == 2:
        return True
    
    # All other even numbers are not prime
    if n % 2 == 0:
        return False
    
    # Check odd divisors up to sqrt(n)
    # We only need to check up to the square root because if n has a divisor
    # greater than sqrt(n), it must also have a corresponding divisor less than sqrt(n)
    i = 3
    while i * i <= n:
        if n % i == 0:
            return False
        i += 2  # Only check odd numbers
    
    return True
```"""
        
        user_prompt = f"""Requirement: {requirement}

Tests to satisfy:
{tests}

{f'Previous feedback to address: {feedback}' if feedback else ''}

Create code that satisfies these tests."""
        
        responses = []
        for agent_type in self.agents.keys():
            print(f"\n{agent_type.value.upper()} is creating code...")
            try:
                messages = [
                    SystemMessage(content=system_prompt),
                    HumanMessage(content=user_prompt)
                ]
                content = self._invoke_agent(agent_type, messages)
                responses.append(AgentResponse(
                    agent_type=agent_type,
                    content=content,
                    success=True
                ))
                print("GENERATED RESPONSE:")
                print(content)
                print("==============================================")
                print(f"✓ {agent_type.value.upper()} completed")
            except Exception as e:
                print(f"✗ {agent_type.value.upper()} failed: {str(e)}")
                responses.append(AgentResponse(
                    agent_type=agent_type,
                    content="",
                    success=False,
                    reasoning=str(e)
                ))
        
        return responses
    
    def validate_code(self, requirement: str, tests: str, 
                     code_responses: List[AgentResponse]) -> Tuple[bool, str]:
        """Step 5: Validate the code created by the third group"""
        print("\n" + "="*80)
        print("STEP 5: Validating Code")
        print("="*80)
        
        # Combine all code responses
        all_code = "\n\n".join([
            f"=== Code from {r.agent_type.value.upper()} ===\n{r.content}"
            for r in code_responses if r.success
        ])
        
        system_prompt = """You are an expert code reviewer. Evaluate the provided code for:
1. Correctness - Does it meet the requirement?
2. Test compatibility - Will it pass the provided tests?
3. Code quality - Is it well-structured and maintainable?
4. Best practices - Does it follow Python best practices?

Respond in the following format:
VALID: [YES/NO]
REASONING: [Your detailed reasoning]
FEEDBACK: [Specific feedback for improvement if invalid]"""
        
        user_prompt = f"""Requirement: {requirement}

Tests:
{tests}

Code to validate:
{all_code}

Validate this code."""
        
        validation_results = []
        for agent_type in self.agents.keys():
            print(f"\n{agent_type.value.upper()} is validating code...")
            try:
                messages = [
                    SystemMessage(content=system_prompt),
                    HumanMessage(content=user_prompt)
                ]
                content = self._invoke_agent(agent_type, messages)
                validation_results.append(content)
                print("GENERATED RESPONSE:")
                print(content)
                print("==============================================")
                print(f"✓ {agent_type.value.upper()} completed validation")
            except Exception as e:
                print(f"✗ {agent_type.value.upper()} validation failed: {str(e)}")
        
        # Analyze validation results (majority vote)
        valid_count = sum(1 for result in validation_results if "VALID: YES" in result.upper())
        is_valid = valid_count >= len(validation_results) / 2
        
        # Combine feedback
        feedback = "\n\n".join([f"=== Validator {i+1} ===\n{result}" 
                                for i, result in enumerate(validation_results)])
        
        print(f"\nValidation Result: {'✓ VALID' if is_valid else '✗ INVALID'}")
        print(f"Valid votes: {valid_count}/{len(validation_results)}")
        
        return is_valid, feedback
    
    def run_tdd_workflow(self, requirement: str) -> Dict[str, Any]:
        """Run the complete TDD workflow"""
        print("\n" + "="*80)
        print("TDD MULTI-AGENT WORKFLOW STARTED")
        print("="*80)
        print(f"Requirement: {requirement}")
        
        # Step 1-3: Create and validate tests
        test_feedback = ""
        final_tests = None
        
        for iteration in range(self.max_iterations):
            print(f"\n--- Test Creation Iteration {iteration + 1} ---")
            
            # Step 1: Create tests
            test_responses = self.create_tests(requirement, test_feedback)
            
            # Step 2: Validate tests
            tests_valid, test_feedback = self.validate_tests(requirement, test_responses)
            
            # Step 3: Check if valid
            if tests_valid:
                final_tests = "\n\n".join([r.content for r in test_responses if r.success])
                print(f"\n✓ Tests validated successfully after {iteration + 1} iteration(s)")
                break
            else:
                print(f"\n✗ Tests invalid, retrying... (Iteration {iteration + 1})")
        
        if not tests_valid:
            return {
                "success": False,
                "error": f"Failed to create valid tests after {self.max_iterations} iterations",
                "feedback": test_feedback
            }
        
        # Step 4-6: Create and validate code
        code_feedback = ""
        final_code = None
        
        for iteration in range(self.max_iterations):
            print(f"\n--- Code Creation Iteration {iteration + 1} ---")
            
            # Step 4: Create code
            code_responses = self.create_code(requirement, final_tests, code_feedback)
            
            # Step 5: Validate code
            code_valid, code_feedback = self.validate_code(
                requirement, final_tests, code_responses
            )
            
            # Step 6: Check if valid
            if code_valid:
                final_code = "\n\n".join([r.content for r in code_responses if r.success])
                print(f"\n✓ Code validated successfully after {iteration + 1} iteration(s)")
                break
            else:
                print(f"\n✗ Code invalid, retrying... (Iteration {iteration + 1})")
        
        if not code_valid:
            return {
                "success": False,
                "error": f"Failed to create valid code after {self.max_iterations} iterations",
                "tests": final_tests,
                "feedback": code_feedback
            }
        
        return {
            "success": True,
            "tests": final_tests,
            "code": final_code,
            "message": "TDD workflow completed successfully!"
        }


def save_outputs(result: Dict[str, Any], requirement: str):
    """Save the generated tests and code to files"""
    if not result["success"]:
        print("\n" + "="*80)
        print("ERROR: Workflow failed")
        print("="*80)
        print(result.get("error", "Unknown error"))
        if "feedback" in result:
            print("\nFeedback:")
            print(result["feedback"])
        return
    
    # Save tests
    tests_file = "./output/generated_tests.py"
    with open(tests_file, "w") as f:
        f.write(f'"""\nTests for: {requirement}\n"""\n\n')
        f.write(result["tests"])
    
    # Save code
    code_file = "./output/generated_code.py"
    with open(code_file, "w") as f:
        f.write(f'"""\nImplementation for: {requirement}\n"""\n\n')
        f.write(result["code"])
    
    print("\n" + "="*80)
    print("SUCCESS: TDD Workflow Completed!")
    print("="*80)
    print(f"\nFiles saved:")
    print(f"  Tests: {tests_file}")
    print(f"  Code:  {code_file}")


def main():
    """Main entry point"""
    print("="*80)
    print("TDD Multi-Agent System")
    print("Powered by Claude, Gemini, and GPT")
    print("="*80)
    
    # Get requirement from command line or prompt
    if len(sys.argv) > 1:
        requirement = " ".join(sys.argv[1:])
    else:
        print("\nEnter your coding requirement:")
        requirement = input("> ").strip()
    
    if not requirement:
        print("Error: No requirement provided!")
        sys.exit(1)
    
    try:
        # Initialize orchestrator
        orchestrator = AgentOrchestrator()
        
        # Run TDD workflow
        result = orchestrator.run_tdd_workflow(requirement)
        
        # Save outputs
        save_outputs(result, requirement)
        
    except Exception as e:
        print(f"\n✗ Fatal error: {str(e)}")
        import traceback
        traceback.print_exc()
        sys.exit(1)

if __name__ == "__main__":
    main()
