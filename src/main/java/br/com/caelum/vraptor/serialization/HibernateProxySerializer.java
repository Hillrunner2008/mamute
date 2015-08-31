package br.com.caelum.vraptor.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import org.hibernate.proxy.HibernateProxy;

public class HibernateProxySerializer implements JsonSerializer<HibernateProxy> {

    @Override
    public JsonElement serialize(HibernateProxy src, Type typeOfSrc, JsonSerializationContext context) {
        Object deProxied = src.getHibernateLazyInitializer().getImplementation();
        return context.serialize(deProxied);
    }
}
