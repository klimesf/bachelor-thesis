
package cz.cvut.fel.adaptiverestfulapi.meta.data;

import cz.cvut.fel.adaptiverestfulapi.meta.Configuration;
import cz.cvut.fel.adaptiverestfulapi.meta.data.base.Item;
import org.testng.annotations.DataProvider;

import java.util.HashMap;


public class Provider {

    @DataProvider(name = "packages")
    public static Object[][] packages() {
        return new Object[][] {
                { "cz.cvut.fel.adaptiverestfulapi.meta.data.simple", Object.class },
                { "cz.cvut.fel.adaptiverestfulapi.meta.data.base", Item.class },
                { "cz.cvut.fel.adaptiverestfulapi.meta.data.abstracts", Object.class },
                { "cz.cvut.fel.adaptiverestfulapi.meta.data.interfaces", Object.class }
        };
    }

}