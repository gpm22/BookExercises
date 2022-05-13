public class Tests {

    static PokeBerryOrder pokeBerryOrder;

    public static void main(String[] args) {
        pokeBerryOrder = new PokeBerryOrder();
        testAddToOrder();
        testGetTotalOrderQuantity();
        testGetTotalOrderCost();
        System.out.println("No erros during the tests, congrats!");
    }

    public static void testAddToOrder() {

        System.out.println("Testing addToOrder...");

        pokeBerryOrder.clearCart();

        pokeBerryOrder.addToOrder(PokeBerry.CHERI, 10);

        if (pokeBerryOrder.getPokeBerryCount(PokeBerry.CHERI) != 10) {
            throw new RuntimeException("Error in testing addToOrder:\n Should be 10, but got: "
                    + pokeBerryOrder.getPokeBerryCount(PokeBerry.CHERI));
        }

        pokeBerryOrder.addToOrder(PokeBerry.CHERI, 10);

        if (pokeBerryOrder.getPokeBerryCount(PokeBerry.CHERI) != 20) {
            throw new RuntimeException("Error in testing addToOrder:\n Should be 20, but got: "
                    + pokeBerryOrder.getPokeBerryCount(PokeBerry.CHERI));
        }

        System.out.println("The addToOrder passed the test!");

    }

    public static void testGetTotalOrderQuantity() {

        System.out.println("Testing getTotalOrderQuantity...");

        pokeBerryOrder.clearCart();

        int quantities = 0;
        for (PokeBerry berry : PokeBerry.values()) {
            quantities++;
            pokeBerryOrder.addToOrder(berry, 1);
        }

        if (pokeBerryOrder.getTotalOrderQuantity() != quantities) {
            throw new RuntimeException(
                    "Error in testing getTotalOrderQuantity:\n Should be " + quantities + ", but got: "
                            + pokeBerryOrder.getTotalOrderQuantity());
        }

        System.out.println("The getTotalOrderQuantity passed the test!");

    }

    public static void testGetTotalOrderCost() {

        System.out.println("Testing getTotalOrderCost...");

        pokeBerryOrder.clearCart();

        int totalCost = 0;
        for (PokeBerry berry : PokeBerry.values()) {
            totalCost += berry.cost();
            pokeBerryOrder.addToOrder(berry, 1);
        }

        if (pokeBerryOrder.getTotalOrderCost() != totalCost) {
            throw new RuntimeException(
                    "Error in testing getTotalOrderCost:\n Should be " + totalCost + ", but got: "
                            + pokeBerryOrder.getTotalOrderCost());
        }

        pokeBerryOrder.clearCart();

        totalCost = 0;
        int quantities = 0;
        for (PokeBerry berry : PokeBerry.values()) {
            totalCost += berry.cost() * (++quantities);
            pokeBerryOrder.addToOrder(berry, quantities);
        }

        if (pokeBerryOrder.getTotalOrderCost() != totalCost) {
            throw new RuntimeException(
                    "Error in testing getTotalOrderCost:\n Should be " + totalCost + ", but got: "
                            + pokeBerryOrder.getTotalOrderCost());
        }

        System.out.println("The getTotalOrderCost passed the test!");

    }
}
