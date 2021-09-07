public class Test8
{
    public static void main(String[] args)
    {
        double gallonPrice = 2.35;
        double milesPerGallon = 40.0;
        double totalFunds = 8.0;
        double numGallons = totalFunds/gallonPrice;
        double numMiles = numGallons * milesPerGallon;
        System.out.println(numMiles);
    }
}
