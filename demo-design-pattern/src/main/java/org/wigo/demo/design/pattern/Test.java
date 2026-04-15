package org.wigo.demo.design.pattern;

/**
 * @author wuwei
 * @since 2023/7/20
 */
public class Test {


    /**
     * 判断一个字符串是否是回文
     * @param args
     * @return
     */
    public static void main(String[] args) {
        String str = "cbbd";
        System.out.println(new Test().isP(str,0,str.length() - 1));
    }


    private boolean isP(String str,int l,int r){
        if(l > r){
            return true;
        }
        if(str.charAt(l) == str.charAt(r)){
            return isP(str,l+1,r-1);
        }
        return false;
    }

}
