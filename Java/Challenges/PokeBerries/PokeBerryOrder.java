import java.util.HashMap;
import java.util.Map;

public class PokeBerryOrder {

    private Map<PokeBerry, Integer> cart;

    /**
     * Initialize a new mapping of PokeBerry types to order quantities.
     */
    public PokeBerryOrder() {
        cart = new HashMap<>();
    }

    /**
     * @param berry The type of PokeBerry being added to the order.
     * @param count The number of units of PokeBerry type 'type' to add to the
     *              order.
     */
    public void addToOrder(PokeBerry berry, int count) {
        if (cart.keySet().contains(berry)) {
            int previousValue = cart.get(berry);
            cart.put(berry, previousValue + count);
        } else {
            cart.put(berry, count);
        }
    }

    /**
     * @param berry The type of PokeBerry
     * @return The total number of units of PokeBerry 'type' in the order.
     */
    public int getPokeBerryCount(PokeBerry berry) {
        return cart.get(berry);
    }

    /**
     * @param berry The type of PokeBerry being ordered
     * @return The total cost of just the PokeBerry units of 'type' in the order.
     */
    public float getPokeBerryCost(PokeBerry berry) {
        return cart.get(berry) * berry.cost();
    }

    /**
     * @return The total number of all types of PokeBerry units in the order.
     */
    public int getTotalOrderQuantity() {
        return cart
                .values()
                .stream()
                .reduce(0, (a, b) -> a + b);
    }

    /**
     * @return The total cost of the order.
     */
    public float getTotalOrderCost() {
        return cart
                .keySet()
                .stream()
                .map((berry) -> getPokeBerryCost(berry))
                .reduce(0f, (a, b) -> a + b);
    }

    /**
     * Clear the cart
     */
    public void clearCart() {
        cart.clear();
    }
}