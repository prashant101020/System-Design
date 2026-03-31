package CreationalDesignPattern;

import java.util.HashMap;
import java.util.Map;

public class Builder {
    public static void main(String[] args){
    HttpRequest http=new HttpRequest.Builder("http://api.gf/").build();
        HttpRequest http2=new HttpRequest.Builder("http://api.gf/")
                .method("POST").addHeader("abc","sadf").build();
    System.out.println(http);
        System.out.println(http2);
    }
}

class HttpRequest{
    private final String url;
    private final String method;
    private final Map<String,String> Headers;
    private final Map<String,String> queryParams;
    private final String body;
    private final int timeout;

    private HttpRequest(Builder builder){
        this.url = builder.url;
        this.method = builder.method;
        this.Headers = builder.Headers;
        this.queryParams = builder.queryParams;
        this.body = builder.body;
        this.timeout = builder.timeout;
    }
public String toString(){
        return  "CreationalDesignPattern.HttpRequest{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", headers=" + Headers +
                ", queryParams=" + queryParams +
                ", body='" + body + '\'' +
                ", timeout=" + timeout +
                '}';
}
    public static class Builder{
        private final String url;
        private  String method="Get";
        private  Map<String,String> Headers=new HashMap<>();
        private  Map<String,String> queryParams=new HashMap<>();
        private String body;
        private int timeout=30000;

        public Builder(String url){
            this.url=url;
        }

        public Builder method(String method){
            this.method=method;
            return this;
        }

        public Builder addHeader(String key,String Value ){
            this.Headers.put(key,Value);
            return this;
        }

        public Builder addQuerParams(String key,String Value ){
            this.queryParams.put(key,Value);
            return this;
        }
        public Builder body(String body){
            this.body=body;
            return this;
        }

        public Builder timeout(int timeout){
            this.timeout=timeout;
            return this;
        }
        public HttpRequest build(){
            return new HttpRequest(this);
        }
    }
}
