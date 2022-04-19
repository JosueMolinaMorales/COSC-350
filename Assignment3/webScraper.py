import requests
from HtmlParser import HtmlParser
from bs4 import BeautifulSoup

URL = "https://www.towson.edu/fcsm/departments/computerinfosci/facultystaff/"
headers = {
    'user-agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36'
}
s = requests.get(URL,headers=headers)
# print(s.text)
# print(s.headers)
# print(s.status_code)
# print(s.cookies)
# print(s.is_redirect)
soup = BeautifulSoup(s.text,"html.parser")
parser = HtmlParser()

for table in soup.findAll('tr'):
    parser.feed(str(table))    