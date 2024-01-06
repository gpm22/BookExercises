import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Chudnovsky {

    private static final BigDecimal SIX = new BigDecimal("6");
    private static final BigDecimal TWO = new BigDecimal("2");
    private static final BigDecimal FIVE = new BigDecimal("5");
    private static final BigDecimal QAB_HELPER = new BigDecimal("10939058860032000");
    private static final BigDecimal RAB_HELPER_1 = new BigDecimal("545140134");
    private static final BigDecimal RAB_HELPER_2 = new BigDecimal("13591409");
    private static final BigDecimal CALCULATE_HELPER_1 = new BigDecimal("426880");
    private static final BigDecimal CALCULATE_HELPER_2 = new BigDecimal("10005");
    private static final BigDecimal CALCULATE_HELPER_3 = new BigDecimal("13591409");
    
    
    public static void main(String[] args) {
    	for(int i=2;i<10;i++)
	    System.out.println("n = " + i + " - " +  calculate(i));
    }
    
    public static BigDecimal calculate(int n){
	return calculate(n, 100);
    }
    
    public static BigDecimal calculate(int n, int precision){
    	MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
    	BigDecimal[] firstIteration = binarySplit(BigDecimal.ONE, new BigDecimal(n));
    	return sqrt(CALCULATE_HELPER_2, mc)
    		.multiply(CALCULATE_HELPER_1)
    		.multiply(firstIteration[1])
    		.divide(CALCULATE_HELPER_3
    		       .multiply(firstIteration[1])
    		       .add(firstIteration[2]), mc);
    }

    private static BigDecimal[] binarySplit(BigDecimal a, BigDecimal b){
    	BigDecimal pab;
    	BigDecimal qab;
    	BigDecimal rab;
    	
  
    	if(b.equals(a.add(BigDecimal.ONE))){
	    pab = SIX.multiply(a).subtract(FIVE)
   	             .multiply(TWO.multiply(a).subtract(BigDecimal.ONE))
		     .multiply(SIX.multiply(a).subtract(BigDecimal.ONE))
	             .negate();
	
	    qab = a.pow(3).multiply(QAB_HELPER);
	
	    rab = RAB_HELPER_1.multiply(a).add(RAB_HELPER_2).multiply(pab);
    	} else {
    	    BigDecimal m = a.add(b).divide(TWO, RoundingMode.FLOOR);
    		
    	    BigDecimal[] am = binarySplit(a, m);
   	    BigDecimal[] mb = binarySplit(m, b);
    		
    	    pab = am[0].multiply(mb[0]);
            qab = am[1].multiply(mb[1]);
    	    rab = mb[1].multiply(am[2]).add(am[0].multiply(mb[2]));
    	}
    	BigDecimal[] result = {pab, qab, rab};
    	return result;
    }

    // Helper method to calculate square root using Newton-Raphson method
    private static BigDecimal sqrt(BigDecimal x, MathContext mc) {
        BigDecimal x0 = BigDecimal.ZERO;
        BigDecimal x1 = new BigDecimal(Math.sqrt(x.doubleValue()), mc); // Initial guess

        while (!x0.equals(x1)) {
            x0 = x1;
            x1 = x.divide(x0, mc);
            x1 = x1.add(x0);
            x1 = x1.divide(TWO, mc);
        }

        return x1;
    }
}
