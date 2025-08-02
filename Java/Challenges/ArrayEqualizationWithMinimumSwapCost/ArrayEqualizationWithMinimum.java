class ArrayEqualizationWithMinimumSwapCost {

    private final Map<Integer, Long> countMap1 = new HashMap<>();
    private final Map<Integer, Long> countMap2 = new HashMap<>();
    private final TreeMap<Integer, Long> numberOfChanges1 = new TreeMap<>();
    private final TreeMap<Integer, Long> numberOfChanges2 = new TreeMap<>();

    private void fillCounts(int[] arr1, int[] arr2){
        for (int i = 0; i < arr1.length; i++) {
            countMap1.put(arr1[i], countMap1.getOrDefault(arr1[i], 0l) + 1l);
            countMap2.put(arr2[i], countMap2.getOrDefault(arr2[i], 0l) + 1l);
        }
    }

    private int fillNumberOfChanges(){
        Set<Integer> allKeys = new HashSet<Integer>();
        allKeys.addAll(countMap1.keySet());
        allKeys.addAll(countMap2.keySet());

        for(Integer key: allKeys){
            long n1 = countMap1.getOrDefault(key, 0l);
            long n2 = countMap2.getOrDefault(key, 0l);

            if(n1 == n2)
                continue;

            long sum = n1 + n2;
            if(sum % 2 != 0)
                return -1;
            
            long numf = sum/2 - Math.min(n1, n2);

            if(n1 > n2)
                numberOfChanges2.put(key, numf);
            else
                numberOfChanges1.put(key, numf);
        }

        return Collections.min(allKeys)*2;
    }

    private static long costOfChange(
        TreeMap<Integer, Long> numberOfChanges1,
        int minValue,
        TreeMap<Integer, Long> numberOfChanges2
    ){
        Map.Entry<Integer, Long> minimalEntry1 = numberOfChanges1.pollFirstEntry();
        Map.Entry<Integer, Long> maxEntry2 = numberOfChanges2.pollLastEntry();

        long valueOfChange = Math.min(
            minValue,
            Math.min(minimalEntry1.getKey(), maxEntry2.getKey())
        );

        if(minimalEntry1.getValue() > maxEntry2.getValue()){
            numberOfChanges1.put(minimalEntry1.getKey(), minimalEntry1.getValue() - maxEntry2.getValue());
            return valueOfChange*maxEntry2.getValue();
        } 

        if(minimalEntry1.getValue() < maxEntry2.getValue())
            numberOfChanges2.put(maxEntry2.getKey(), maxEntry2.getValue() - minimalEntry1.getValue());

        return valueOfChange*minimalEntry1.getValue(); 
        
    }

    public long minCost(int[] arr1, int[] arr2) {
        this.fillCounts(arr1, arr2);

        int minValue = this.fillNumberOfChanges();

        if(minValue < 0)
            return minValue;

        long changeTotal = 0;

        while(!numberOfChanges1.isEmpty()){
            changeTotal+=costOfChange(numberOfChanges1, minValue, numberOfChanges2);
            if(!numberOfChanges2.isEmpty())
                changeTotal+=costOfChange(numberOfChanges2, minValue, numberOfChanges1);
        }
      
        return changeTotal;
    }

}
