package com.example.jna;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;


/**
 * @author wangzhichao
 * @since 2022/6/9
 */
public class ArrInfo extends Structure {
    public Pointer vals;
    public int len;

    public ArrInfo(Pointer vals, int len) {
        this.vals = vals;
        this.len = len;
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[]{"vals", "len"});
    }

    public static class ByValue extends ArrInfo implements Structure.ByValue {

        public ByValue(Pointer vals, int len) {
            super(vals, len);
        }
    }
}
