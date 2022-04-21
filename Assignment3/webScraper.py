import requests
from bs4 import BeautifulSoup
from html.parser import HTMLParser

tableCount = 0
outFile = open('Professors.txt', 'w')
tableNames = ['Full Time Faculty', 'Full Time Staff', 'Faculty Emeriti']
class HtmlParser(HTMLParser):

    def handle_starttag(self, tag: str, attrs) -> None:
        if tag == 'table':
            outFile.write(tableNames[tableCount]+'\n')

    def handle_endtag(self, tag: str) -> None:
        global tableCount
        if tag == 'tr':
            outFile.write('\n\n')
        elif tag == 'table':
            outFile.write('END' + tableNames[tableCount] + '\n\n')
            tableCount += 1

    def handle_data(self, data: str) -> None:
        if not data.isspace() and self.lasttag in ['p', 'strong', 'a', 'td', 'b', 'span']:
            outFile.write(data + "\n")

    def feed(self, data: str) -> None:
        return super().feed(data)

def main():
    URL = "https://www.towson.edu/fcsm/departments/computerinfosci/facultystaff/"
    headers = {
        'user-agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36'
    }
    s = requests.get(URL,headers=headers)

    soup = BeautifulSoup(s.text,"html.parser")
    parser = HtmlParser()

    for tableRow in soup.findAll('table'):
        parser.feed(str(tableRow))    

    outFile.close()
    print('./Professors.txt has been created successfully')

if __name__ == '__main__':
    main()