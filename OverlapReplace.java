import java.util.*;

public class OverlapReplace {
    public static void main(String args[]) {
        String s = "", t = "", r = "";
        while (s.length() < 100001) s += "a";
        while (t.length() < 50001) t += "a";
        while (r.length() < 50001) r += "b";
        System.out.println("Generated inputs...");
        overlappingFindAndReplace(s, t, r);
    }
    
    static String overlappingFindAndReplace(String text, String pattern, String replacement) {
        long x = System.currentTimeMillis();
        List<Integer> indexes = getAllOccurrences(text, pattern);
        //System.out.println(indexes);
        for (int i = 1; i < indexes.size(); i++) {
            int a = indexes.get(i-1), b = indexes.get(i), c = b-a;
            //if (c < pattern.length()) text = text.replaceFirst(pattern.substring(0, c), replacement.substring(0, c));
            //else text = text.replaceFirst(pattern, replacement);
            //
            //System.out.println("Sub - " + text.substring(a, b+1));
            
            text = text.substring(0, a) +
                (c < pattern.length() ? text.substring(a, b+1).replaceFirst(pattern.substring(0, c), replacement.substring(0, c)) : text.substring(a, b+1).replaceFirst(pattern, replacement)) +
                text.substring(b+1);

            //if (i % 1000 == 0) 
                //System.out.println(System.currentTimeMillis() - x);
            
            //System.out.println(text);
        }
        //System.out.println(text);
        
        System.out.println(System.currentTimeMillis() - x);
        return text.replace(pattern, replacement);
    }

    static String overlappingFindAndReplace1(String text, String pattern, String replacement) {
        long x = System.currentTimeMillis();
        List<Integer> indexes = getAllOccurrences(text, pattern);
        if (indexes.size() < 1) return text;
        char[] newChar = new char[text.length()], txt = text.toCharArray(), rep = replacement.toCharArray();
        int lastB = indexes.get(0), d = 0;
        for (; d < indexes.get(0); d++) newChar[d] = txt[d];
        //System.out.println(indexes);
        for (int i = 1; i < indexes.size(); i++) {
            int a = indexes.get(i-1), b = indexes.get(i), c = b-a;
            //System.out.printf("a = %d, b = %d, c = %d\n", a, b, c);
            lastB = b;
            
            if (c < pattern.length()) {
                for (int j = a, k = 0; j < b; j++, k++) newChar[d++] = rep[k];
            } else {
                for (int k = 0; k < pattern.length(); k++) newChar[d++] = rep[k];
                if (c > pattern.length()) 
                    for (int k = a + pattern.length(); k < b; k++) newChar[d++] = txt[k];
            }
            //if (c < pattern.length()) text = text.replaceFirst(pattern.substring(0, c), replacement.substring(0, c));
            //else text = text.replaceFirst(pattern, replacement);
            //
            //System.out.println("Sub - " + text.substring(a, b+1));
            
            //System.out.println(Arrays.toString(newChar));
            
            //System.out.println(text);
        }
        String newText = String.valueOf(newChar).replace("\u0000", "");
        //System.out.println(newText + " " + lastB);
        if (lastB < text.length()) newText += text.substring(lastB);
        //System.out.println(text);
        newText = newText.replace(pattern, replacement);
        System.out.println(System.currentTimeMillis() - x);
        return newText;
    }

    
    static List<Integer> getAllOccurrences2(String text, String pattern) {
        long x = System.currentTimeMillis();
        List<Integer> indexes = new ArrayList<>();
        int l = pattern.length(), n = text.length();
        char pt[] = pattern.toCharArray(), sub[], txt[] = text.toCharArray();
        for (int i = 0; i <= n - l && l > 0; i++) {
            sub = Arrays.copyOfRange(txt, i, i+l);
            if (Arrays.equals(sub, pt)) {
                //System.out.println("[INDEX] Adding " + i);
                indexes.add(i);
            }
        }
        System.out.println("Index search - " + (System.currentTimeMillis() - x));
        return indexes;
    }

    
    static List<Integer> getAllOccurrences(String text, String pattern) {
        long x = System.currentTimeMillis();
        List<Integer> indexes = new ArrayList<>();
        if (!pattern.isEmpty()) {
            int index = text.indexOf(pattern);
            indexes.add(index);
            while (index >= 0) indexes.add(index = text.indexOf(pattern, index + 1));
            indexes.remove(new Integer(-1));
        }
        System.out.println("Index search - " + (System.currentTimeMillis() - x));
        return indexes;
        
    }
}