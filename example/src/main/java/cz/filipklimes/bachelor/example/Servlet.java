
package cz.filipklimes.bachelor.example;

import cz.cvut.fel.adaptiverestfulapi.caching.IfModifiedSinceCache;
import cz.cvut.fel.adaptiverestfulapi.core.Filter;
import cz.cvut.fel.adaptiverestfulapi.data.Dispatcher;
import cz.filipklimes.bachelor.example.security.Users;
import cz.cvut.fel.adaptiverestfulapi.serialization.Resolver;
import cz.cvut.fel.adaptiverestfulapi.servlet.FilteredServlet;


public class Servlet extends FilteredServlet {

    public Servlet() {
        Filter authentication = Users.getInstance().getAuthentication();
        Filter authorization = Users.getInstance().getMethodAuthorization();
        Filter cache = new IfModifiedSinceCache();
        Filter serializer = new Resolver();
        Filter data = new Dispatcher();

        authentication.setNext(authorization.setNext(cache.setNext(serializer.setNext(data))));
        this.filter = authentication;
    }

}
