
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.representations;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Girish
 */
public class NextStateURIMapperAdapter extends XmlAdapter<NextStateURIMapper[], Map<String, String>> {

    @Override
    public NextStateURIMapper[] marshal(Map<String, String> map) throws Exception {
        NextStateURIMapper[] nextStateURIMappers = new NextStateURIMapper[map.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            nextStateURIMappers[i++] = new NextStateURIMapper(entry.getKey(), entry.getValue());
        }
        return nextStateURIMappers;
    }

    @Override
    public Map<String, String> unmarshal(NextStateURIMapper[] nextStateURIMappers) throws Exception {
        Map<String, String> map = new HashMap<>();
        for (NextStateURIMapper nextStateURIMapper : nextStateURIMappers) {
            map.put(nextStateURIMapper.key, nextStateURIMapper.value);
        }
        return map;
    }
}
