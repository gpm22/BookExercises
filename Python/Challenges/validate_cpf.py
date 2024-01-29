def validate_cpf(cpf):
    
    if not isinstance(cpf, str):
        return False
    # Remove all non-digit characters
    cpf = remove_non_digit_characters(cpf)
    
    return cpf_format_checker(cpf) and cpf_calculation_checker(cpf)
    
def remove_non_digit_characters(_str):
    return "".join(filter(str.isdigit, _str))

def cpf_format_checker(cpf):
    return len(cpf) == 11

def cpf_calculation_checker(cpf):
    numbers = [int(digit) for digit in cpf]

    return cpf_digit_checker(numbers, 1) and cpf_digit_checker(numbers, 2) and invalid_known_cpf_checker(numbers)

def cpf_digit_checker(cpf_digits, digit_checker):
    digit_position = digit_checker == 1 and 9 or 10

    sum = 0

    for i in range(digit_position):
        sum += cpf_digits[i] * (digit_position + 1 - i)

    comparing_value = (sum * 10) % 11

    if comparing_value == 10:
        comparing_value = 0

    return cpf_digits[digit_position] == comparing_value

def invalid_known_cpf_checker(cpf_digits):
    for i in range(1, len(cpf_digits)):
        if cpf_digits[i - 1] != cpf_digits[i]:
            return True
    return False
