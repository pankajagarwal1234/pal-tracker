package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {
    private String port;
    private String memoryLimit;
    private String cfInstanceIndex;
    private String cfInstanceAddr;
    public EnvController(@Value("${PORT:8080}") String port1, @Value("${MEMORY_LIMIT:1GB}") String memoryLimit1, @Value("${CF_INSTANCE_INDEX:NOT_SET}") String cfInstanceIndex1, @Value("${CF_INSTANCE_ADDR:NOT_SET}") String cfInstanceAddr1)
    {

        this.port = port1;
        this.memoryLimit = memoryLimit1;
        this.cfInstanceIndex = cfInstanceIndex1;
        this.cfInstanceAddr = cfInstanceAddr1;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv(){
        Map<String, String> env = new HashMap<String,String>();
        env.put("PORT",port);
        env.put("MEMORY_LIMIT",memoryLimit);
        env.put("CF_INSTANCE_INDEX",cfInstanceIndex);
        env.put("CF_INSTANCE_ADDR",cfInstanceAddr);
        return env;
    }
}
