require_relative "./decodePyramid"
require "test/unit"
extend Test::Unit::Assertions

def test(filePath, expected)
    result = decodePyramid(filePath)
    assert_equal(expected, result, filePath)
    puts "success! #{filePath}"
end

test('./test2.txt', 'My name is Gabriel')
test('./test1.txt', 'I enjoy Algorithms')