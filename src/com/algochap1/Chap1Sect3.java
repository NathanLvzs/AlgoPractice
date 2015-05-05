package com.algochap1;

import com.stdlib.StdIn;
import com.stdlib.StdOut;

/**
 * Some practice of the section 3, chapter 1
 * Created by Nathan on 5/3/2015.
 */
public class Chap1Sect3 {

    /**
     * 书上的双栈算术表达式求值算法
     *
     * @param args
     */
    public static void Evaluate(String[] args) throws Exception {
        LinkedStack<String> ops = new LinkedStack<>();
        LinkedStack<Double> vals = new LinkedStack<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("(")) ;
            else if (s.equals("+"))
                ops.push(s);
            else if (s.equals("-"))
                ops.push(s);
            else if (s.equals("*"))
                ops.push(s);
            else if (s.equals("/"))
                ops.push(s);
            else if (s.equals("sqrt"))
                ops.push(s);
            else if (s.equals(")")) {
                String op = ops.pop();
                double v = vals.pop();
                if (op.equals("+"))
                    v = vals.pop() + v;
                else if (op.equals("-"))
                    v = vals.pop() - v;
                else if (op.equals("*"))
                    v = vals.pop() * v;
                else if (op.equals("/"))
                    v = vals.pop() / v;
                else if (op.equals("sqrt"))
                    v = Math.sqrt(v);
                vals.push(v);
            }
            else
                vals.push(Double.parseDouble(s));
        }
        StdOut.println(vals.pop());
    }

    // 1.3.9：从标准输入得到一个缺少左括号的表达式并打印出补全括号之后的中序表达式
    // 例如：输入 1+2)*3-4)*5-6))) 输出应为 ((1+2)*((3-4)*(5-6)))
    public static String completeParentheses(String[] inputs) throws Exception {
        LinkedStack<String> ops = new LinkedStack<>();
        LinkedStack<String> vals = new LinkedStack<>();
        for (String str : inputs) {
            if (str.equals("+"))
                ops.push(str);
            else if (str.equals("-"))
                ops.push(str);
            else if (str.equals("*"))
                ops.push(str);
            else if (str.equals("/"))
                ops.push(str);
            else if (str.equals(")")) {
                String op = ops.pop();
                String val = vals.pop();
                String temp = "(" + vals.pop() + op + val + ")";
                vals.push(temp);
            }
            else
                vals.push(str);
        }
        return vals.pop();
    }

    public static void completeParenthesesTest() throws Exception {
        System.out.println();
        System.out.println("1+2)*3-4)*5-6)))");
        String[] inputs = new String[16];
        inputs[0] = "1";
        inputs[1] = "+";
        inputs[2] = "2";
        inputs[3] = ")";
        inputs[4] = "*";
        inputs[5] = "3";
        inputs[6] = "-";
        inputs[7] = "4";
        inputs[8] = ")";
        inputs[9] = "*";
        inputs[10] = "5";
        inputs[11] = "-";
        inputs[12] = "6";
        inputs[13] = ")";
        inputs[14] = ")";
        inputs[15] = ")";
        System.out.println(completeParentheses(inputs));
    }

    // 1.3.10：将算术表达式由中序表达式转为后序表达式
    // 在遇到操作数时直接打印，遇到右括号时弹出打印操作符即可
    // 示例：输入(2+((3+4)*(5*6))) 输出 2 3 4 + 5 6 * * +
    public static void infixToPostfix(String[] inputs) throws Exception {
        LinkedStack<String> ops = new LinkedStack<>();
        for (String str : inputs) {
            if (str.equals("("))
                StdOut.print("");
            else if (str.equals(")")) {
                StdOut.print(ops.pop() + " ");
            }
            else if (str.equals("+"))
                ops.push(str);
            else if (str.equals("-"))
                ops.push(str);
            else if (str.equals("*"))
                ops.push(str);
            else if (str.equals("/"))
                ops.push(str);
            else
                StdOut.print(str + " ");
        }
        System.out.println();
    }

    public static void infixToPostfixTest() throws Exception {
        System.out.println();
        System.out.println("(2+((3+4)*(5*6)))");
        String[] inputs = new String[17];
        inputs[0] = "(";
        inputs[1] = "2";
        inputs[2] = "+";
        inputs[3] = "(";
        inputs[4] = "(";
        inputs[5] = "3";
        inputs[6] = "+";
        inputs[7] = "4";
        inputs[8] = ")";
        inputs[9] = "*";
        inputs[10] = "(";
        inputs[11] = "5";
        inputs[12] = "*";
        inputs[13] = "6";
        inputs[14] = ")";
        inputs[15] = ")";
        inputs[16] = ")";
        infixToPostfix(inputs);
    }


    // 1.3.11：从标准输入中得到一个后序表达式，求值并打印结果
    // 2 3 4 + 5 6 * * +  -->  212
    public static void evaluatePostfix(String[] inputs) throws Exception {
        LinkedStack<Integer> vals = new LinkedStack<>();
        for (String str : inputs) {
            if (str.equals("+"))
                vals.push(vals.pop() + vals.pop());
            else if (str.equals("-"))
                vals.push(vals.pop() - vals.pop());
            else if (str.equals("*"))
                vals.push(vals.pop() * vals.pop());
            else if (str.equals("/"))
                vals.push(vals.pop() / vals.pop());
            else
                vals.push(Integer.parseInt(str));
        }
        double result = vals.pop();
        StdOut.println(result);
    }

    public static void evaluatePostfixTest() throws Exception {
        System.out.println();
        System.out.println("2 3 4 + 5 6 * * + ");
        String[] inputs = new String[9];
        inputs[0] = "2";
        inputs[1] = "3";
        inputs[2] = "4";
        inputs[3] = "+";
        inputs[4] = "5";
        inputs[5] = "6";
        inputs[6] = "*";
        inputs[7] = "*";
        inputs[8] = "+";
        evaluatePostfix(inputs);
    }

    public static void main(String[] args) throws Exception {
        completeParenthesesTest();
        infixToPostfixTest();
        evaluatePostfixTest();
    }
}
