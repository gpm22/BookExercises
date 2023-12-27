def decodePyramid(codedMessagePath)
    messageHash = createHashFromFile(codedMessagePath)
    decodedArr = createDecodedIntegersArr(messageHash.keys.sort)
    decodedArr.map {|element| messageHash[element]}.join(' ')
end

def createDecodedIntegersArr(arr)
    nextNumber=0
    counter = 2
    decodedArr = []
    while nextNumber < arr.size
        decodedArr.push(arr[nextNumber])
        nextNumber += counter
        counter += 1
    end
    decodedArr
end

def createHashFromFile(codedMessagePath)
    messageHash = {}
    File.open(codedMessagePath).each_line do |line|
        values = line.split(" ")
        messageHash[values[0].to_i] = values[1]
    end
    messageHash
end
