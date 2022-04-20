from html.parser import HTMLParser

count = 0
outFile = open('Professors.txt', 'w')
class HtmlParser(HTMLParser):

    def handle_starttag(self, tag: str, attrs) -> None:
        if tag == 'td': # This is a column within a row
            pass
        elif tag == 'table':
            outFile.write('TABLE START')

    def handle_endtag(self, tag: str) -> None:
        #print("Encountered an end tag :", tag)
        if tag == 'tr':
            outFile.write('\n\n')
            print("END OF TR TAG\n\n")
        elif tag == 'table':
            outFile.write('TABLE END')
            print("END OF TABLE\n\n")

    def handle_data(self, data: str) -> None:
        if not data.isspace():
            if self.lasttag in['p', 'strong', 'a', 'td', 'b']:
                outFile.write(data)

    def feed(self, data: str) -> None:
        return super().feed(data)