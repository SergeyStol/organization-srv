package com.rst.organizationssrv;

public class App {
    public static void main(String[] args) {
        System.out.println(new App().isValid("([]{[]})"));
    }

    public boolean isValid(String s) {
        if (s.length() % 2 != 0) return false;
        int top = -1;
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            if (top < 0 || !isMatch(sb.charAt(top), sb.charAt(i))) {
                top++;
                sb.setCharAt(top, sb.charAt(i));
            } else {
                top--;
            }
        }
        return top == -1;
    }

    private boolean isMatch(char c1, char c2) {
        return c1 == '(' && c2 == ')' ||
                c1 == '[' && c2 == ']' ||
                c1 == '{' && c2 == '}';
    }
}
