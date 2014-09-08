package uz.javlon.beecell.utils;

import java.util.regex.Pattern;

/**
 * @author javasparx  [9/4/14]
 */
public class Utils {

    private static final String regex = "^9[0134]\\d{7}$";
    private static final String regexUcell = "^9[34]\\d{7}$";
    private static final String regexBeeline = "^9[01]\\d{7}$";
    private static Pattern pattern = Pattern.compile(regex);
    private static Pattern patternUcell = Pattern.compile(regexUcell);
    private static Pattern patternBeeline = Pattern.compile(regexBeeline);

    public static boolean validPhone(String phone) {
        return pattern.matcher(phone).matches();
    }

    public static boolean isUcell(String phone) {
        return patternUcell.matcher(phone).matches();
    }

    public static boolean isBeeline(String phone) {
        return patternBeeline.matcher(phone).matches();
    }
}
