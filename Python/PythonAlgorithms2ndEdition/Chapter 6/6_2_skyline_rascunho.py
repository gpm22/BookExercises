def skyline_1(buildings):

	edges = []
	edges.extend([building[0],building[2]] for building in buildings)
	print(edges)
	edges = sorted(sum(edges,[])) #sorting and flatening the list of building edges
	print(edges)
 
	current = 0
	points = []
  
	for i in edges:
		active = []
		active.extend(building for building in buildings if (building[0] <= i and building[2] > i)) 
	  #current observed point is within borders of these buildings (active buildings)
		print(i,active)
		if not active: 
	    #if there is no active buildings, highest point is 0
	    		current = 0
	    		points.append((i,0))
	    		continue
	  
		max_h = max(building[1] for building in active)
		if max_h != current:
		#if current highest point is lower then highest point of current active buildings change current highest point
			current = max_h
			points.append((i,max_h))
     
	return points
	
	
	
	
	
class Solution(object):
    def getSkyline(self, blds):
        """
        :type blds: List[List[int]]
        :rtype: List[List[int]]
        """
        if not blds: return []
        if len(blds) == 1: return [[blds[0][0], blds[0][2]], [blds[0][1], 0]]
        mid = len(blds) // 2
        left = self.getSkyline(blds[:mid])
        right = self.getSkyline(blds[mid:])
        return self.merge(left, right)
    
    def merge(self, left, right):
        h1, h2, res = 0, 0, []
        while left and right:
            if left[0][0] < right[0][0]:
                pos, h1 = left[0]
                left = left[1:]
            elif left[0][0] > right[0][0]:
                pos, h2 = right[0]
                right = right[1:]
            else:
                pos, h1 = left[0]
                h2 = right[0][1]
                left = left[1:]
                right = right[1:]
            H = max(h1, h2)
            if not res or H != res[-1][1]:
                res.append([pos, H])
        if left:
            res += left
        if right:
            res += right
        return res

