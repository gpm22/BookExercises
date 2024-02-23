import unittest

from validate_cpf import validate_cpf

class TestValidateCpf(unittest.TestCase):
        
    def test_invalid_types(self) -> None:
        self.assertFalse(validate_cpf(None))
        self.assertFalse(validate_cpf(77127916243))
        self.assertFalse(validate_cpf(15657.000))
        self.assertFalse(validate_cpf(12167281j))
        
    def test_empty_cpf(self) -> None:
        self.assertFalse(validate_cpf(""))

    def test_cpf_with_letters(self) -> None:
        self.assertFalse(validate_cpf("12345abcde6"))

    def test_cpf_with_symbols(self) -> None:
        self.assertFalse(validate_cpf("123.456.789-0@"))
        self.assertFalse(validate_cpf("0513$792180"))
        self.assertFalse(validate_cpf("#5138792180"))

    def test_short_cpf(self) -> None:
        self.assertFalse(validate_cpf("1234567890"))

    def test_long_cpf(self) -> None:
        self.assertFalse(validate_cpf("123456789012"))

    def test_valid_cpf(self) -> None:
        self.assertTrue(validate_cpf("051.387.921-80"))
        self.assertTrue(validate_cpf("05138792180"))
        self.assertTrue(validate_cpf("015.267.810-77"))
        self.assertTrue(validate_cpf("01526781077"))

    def test_invalid_check_digits(self) -> None:
        self.assertFalse(validate_cpf("123.456.789-00"))
        self.assertFalse(validate_cpf("123.456.789-12"))
        self.assertFalse(validate_cpf("912.373.780-40"))
        self.assertFalse(validate_cpf("12345678900"))
        self.assertFalse(validate_cpf("12345678912"))
        self.assertFalse(validate_cpf("91237378040"))

    def test_known_invalid_cpf(self) -> None:
        self.assertFalse(validate_cpf("000.000.000-00"))
        self.assertFalse(validate_cpf("111.111.111-11"))
        self.assertFalse(validate_cpf("222.222.222-22"))
        self.assertFalse(validate_cpf("333.333.333-33"))
        self.assertFalse(validate_cpf("444.444.444-44"))
        self.assertFalse(validate_cpf("555.555.555-55"))
        self.assertFalse(validate_cpf("666.666.666-66"))
        self.assertFalse(validate_cpf("777.777.777-77"))
        self.assertFalse(validate_cpf("888.888.888-88"))
        self.assertFalse(validate_cpf("999.999.999-99"))
        self.assertFalse(validate_cpf("00000000000"))
        self.assertFalse(validate_cpf("11111111111"))
        self.assertFalse(validate_cpf("22222222222"))
        self.assertFalse(validate_cpf("33333333333"))
        self.assertFalse(validate_cpf("44444444444"))
        self.assertFalse(validate_cpf("55555555555"))
        self.assertFalse(validate_cpf("66666666666"))
        self.assertFalse(validate_cpf("77777777777"))
        self.assertFalse(validate_cpf("88888888888"))
        self.assertFalse(validate_cpf("99999999999"))

if __name__ == '__main__':
    unittest.main()
