import json
import csv
import xml.etree.ElementTree as ET
from io import StringIO

class DataConverter:
    def convert_data(self, data):
        """Template method"""
        cleaned_data = self.clean_data(data)
        return self.format_data(cleaned_data)

    def clean_data(self, data):
        """Hook for optional data cleaning"""
        return data

    def format_data(self, data):
        raise NotImplementedError()
        
    def get_file_extension(self):
        raise NotImplementedError()
        
    def generate_file(self, data, filename="output"):
        formatted_data = self.format_data(data)
        filename = filename + "." + self.get_file_extension()
        with open(filename, 'w') as output_file:
            output_file.write(formatted_data)

class JSONConverter(DataConverter):
    def format_data(self, data):
        return json.dumps(data)
        
    def get_file_extension(self):
        return "json"
        
class CSVConverter(DataConverter):
    def format_data(self, data):
        output = StringIO()  # Temporary in-memory file
        fieldnames = data.keys()
        writer = csv.DictWriter(output, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerow(data)
        return output.getvalue()  # Return CSV as a string
    
    def get_file_extension(self):
        return "csv"

class XMLConverter(DataConverter):
    def format_data(self, data: dict) -> str:
        root = ET.Element('data')
        self.__format_data_helper(root, data)
        return ET.tostring(root).decode('utf-8')
        
    def __format_data_helper(self, parent: ET.Element, data: dict) -> str:

        for key, value in data.items():
            child = ET.SubElement(parent, key)
            if (isinstance(value, dict)):
                child.text = self.__format_data_helper(child, value)
            else:
                child.text = str(value)
        
    def get_file_extension(self):
        return "xml"

if __name__ == "__main__":
    # Sample usage
    sample_data = {'name': 'Alice', 'age': 30, 'city': 'New York'}

    json_converter = JSONConverter()
    json_data = json_converter.convert_data(sample_data)
    json_converter.generate_file(sample_data)
    print(json_data)

    csv_converter = CSVConverter()
    csv_data = csv_converter.convert_data(sample_data)  # Creates 'output.csv'
    csv_converter.generate_file(sample_data)
    print(csv_data)
    
    xml_converter = XMLConverter()
    xml_data = xml_converter.convert_data(sample_data)
    xml_converter.generate_file(sample_data)
    print(xml_data)
