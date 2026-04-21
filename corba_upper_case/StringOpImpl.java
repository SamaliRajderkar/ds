import StringApp.StringOperationPOA;

public class StringOpImpl extends StringOperationPOA{
    public String process(String input){
        return input.toUpperCase();
    }
}