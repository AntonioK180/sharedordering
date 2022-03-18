package serverapp.selenium;

import serverapp.selenium.waterstones.WaterStonesURLParser;

// Factory Builder pattern
public class StoreURLParserBuilder {

    public StoreURLParser getURLParser(String parserType) {
        switch(parserType) {
            case "waterstones.com":
                return new WaterStonesURLParser();

            case "amazon.com":
                return new WaterStonesURLParser();
        }

        return null;
    }

}
