/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.lang.Math;
/**
 *
 * @author marco.bonomi
 */


public class MainController {
    
    private String[] Expression_to_parts(String expression)
    {
        int c;
        expression = expression.replace("+","!+!");
        expression = expression.replace("-","!-!");
        expression = expression.replace("*","!*!");
        expression = expression.replace("/","!/!");
        expression = expression.replace("^","!^!");
        expression = expression.replace("(","!(!");
        expression = expression.replace(")","!)!");
        String[] parts = expression.split("!");
        c = parts.length;
        for (int i=0;i<c;i++)
            if ("".equals(parts[i]))
            {
                remove_string_from_array(parts, i);
                c--;
                i--;
            }
        
        return parts;
    }
    
    private void remove_string_from_array(String[] array,int index)
    {
        int i;
        for (i=index;i<array.length-1;i++) array[i] = array[i+1];
        array[i] = "";
    }
    
    private void exp(String[] array, int index) {
        double num = Double.parseDouble(array[index-1]);
        double exp = Double.parseDouble(array[index+1]);
        array[index-1] = Double.toString(Math.pow(num,exp));
        remove_string_from_array(array, index);
        remove_string_from_array(array, index);
    }
    
    private void div( String[] array, int index) {
        float num1 = Float.parseFloat(array[index-1]);
        float num2 = Float.parseFloat(array[index+1]);
        array[index-1] = Float.toString(num1/num2);
        remove_string_from_array(array, index);
        remove_string_from_array(array, index);
    }
    
    private void mult( String[] array, int index) {
        float num1 = Float.parseFloat(array[index-1]);
        float num2 = Float.parseFloat(array[index+1]);
        array[index-1] = Float.toString(num1*num2);
        remove_string_from_array(array, index);
        remove_string_from_array(array, index);
    }
    
    private void add( String[] array, int index) {
        float num1 = Float.parseFloat(array[index-1]);
        float num2 = Float.parseFloat(array[index+1]);
        array[index-1] = Float.toString(num1+num2);
        remove_string_from_array(array, index);
        remove_string_from_array(array, index);
    }
    
    private void sub( String[] array, int index) {
        float num1 = Float.parseFloat(array[index-1]);
        float num2 = Float.parseFloat(array[index+1]);
        array[index-1] = Float.toString(num1-num2);
        remove_string_from_array(array, index);
        remove_string_from_array(array, index);
    }
    
    private void brackets(String[] array, int index) {
        int br = 0;
        int c;
        int j;
        
        for (c=index;c<array.length;c++)
        {
            if ("(".equals(array[c]))
                br++;
            else if (")".equals(array[c]))
            {
                br--;
                if (br==0)
                {
                    String[] br_expression_parts = new String[c-index-1];
                    for(j=index;j<c-1;j++)
                        br_expression_parts[j-index] = array[j+1];
                    array[index] = Float.toString(calculate(String.join("",br_expression_parts)));
                    for(j=index+1;j<=c;j++)
                        remove_string_from_array(array, index+1);
                    index=0;
                }
            }
        }
    }
    
    public float calculate(String expression) {
        int i;
        String[] expression_parts;
        
        expression_parts = Expression_to_parts(expression);
        
        for (i=0;i<expression_parts.length;i++)
            if ("(".equals(expression_parts[i]))
            {
                brackets(expression_parts, i);
            }
        for (i=0;i<expression_parts.length;i++)
            if ("^".equals(expression_parts[i]))
            {
                exp(expression_parts,i);
                i--;
            }
        for (i=0;i<expression_parts.length;i++)
            if ("/".equals(expression_parts[i]))
            {
                div(expression_parts,i);
                i--;
            }
        for (i=0;i<expression_parts.length;i++)
            if ("*".equals(expression_parts[i]))
            {
                mult(expression_parts,i);
                i--;
            }
        for (i=0;i<expression_parts.length;i++)
            if ("-".equals(expression_parts[i]))
            {
                sub(expression_parts,i);
                i--;
            }
        for (i=0;i<expression_parts.length;i++)
            if ("+".equals(expression_parts[i]))
            {
                add(expression_parts,i);
                i--;
            }        

        return Float.parseFloat(expression_parts[0]);
    }
    
    @FXML
    private TextField txtField;
    @FXML
    private Label txtLabel;
    
    @FXML
    private void initialize(){
    }
    
    private void write(String symbol)
    {
        txtField.setText(txtField.getText()+symbol);
    }
    
    @FXML
    private void write0()
    {
        write("0");
    }
    @FXML
    private void write1()
    {
        write("1");
    }
    @FXML
    private void write2()
    {
        write("2");
    }
    @FXML
    private void write3()
    {
        write("3");
    }
    @FXML
    private void write4()
    {
        write("4");
    }
    @FXML
    private void write5()
    {
        write("5");
    }
    @FXML
    private void write6()
    {
        write("6");
    }
    @FXML
    private void write7()
    {
        write("7");
    }
    @FXML
    private void write8()
    {
        write("8");
    }
    @FXML
    private void write9()
    {
        write("9");
    }
    @FXML
    private void write_plus()
    {
        write("+");
    }
    @FXML
    private void write_minus()
    {
        write("-");
    }
    @FXML
    private void write_mult()
    {
        write("*");
    }
    @FXML
    private void write_div()
    {
        write("/");
    }
    @FXML
    private void write_exp()
    {
        write("^");
    }
    @FXML
    private void write_dot()
    {
        write(".");
    }
    @FXML
    private void write_brackets_open()
    {
        write("(");
    }
    @FXML
    private void write_brackets_close()
    {
        write(")");
    }
    @FXML
    private void result()
    {
        try {
            txtLabel.setText(Float.toString(calculate(txtField.getText())));
        }
        catch(Exception e) {
            txtLabel.setText("An error occurred");
        }
        
    }
}