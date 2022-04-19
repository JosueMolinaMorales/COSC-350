from html.parser import HTMLParser

class HtmlParser(HTMLParser):

    def handle_starttag(self, tag: str, attrs) -> None:
        pass

    def handle_endtag(self, tag: str) -> None:
        #print("Encountered an end tag :", tag)
        pass

    def handle_data(self, data: str) -> None:
        if not data.isspace():
            if self.lasttag == 'p':
                print("Encountered some data  :", data)
            if self.lasttag == 'strong': # Name of the professor
                print("Name of prof: " + data)
            if self.lasttag == 'a':
                print(f"{data=}")
            if self.lasttag == 'td': # expertise
                print(f"{data=}")

    def feed(self, data: str) -> None:
        return super().feed(data)