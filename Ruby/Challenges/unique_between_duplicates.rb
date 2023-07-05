# receiving a list of duplicates elements and one unique non-duplicate
# return the non-duplicate element
# if the list has one element, return it
require 'benchmark'

def linear_solution(arr)
  return arr[0] if arr.size == 1

  return arr[0] if arr[0] != arr[1]

  for i in 2..(arr.size - 2) do
    return arr[i] if arr[i] != arr[i - 1] && arr[i] != arr[i + 1]
  end

  arr[-1]
end

def log_solution(arr)
  return arr[0] if arr.size == 1

  middle = (arr.size - 1) / 2

  return arr[middle] if arr[middle] != arr[middle - 1] && arr[middle] != arr[middle + 1]

  return log_solution(arr.drop(middle + 1))  if arr[middle] == arr[middle - 1] && middle.odd?

  return log_solution(arr.take(middle - 1))  if arr[middle] == arr[middle - 1] && middle.even?

  return log_solution(arr.take(middle)) if middle.odd?

  log_solution(arr.drop(middle + 2))
end

def tests(&block)
  test([1], 1, &block)
  test([1, 2, 2], 1, &block)
  test([2, 2, 1], 1, &block)
  test([2, 2, 1, 3, 3], 1, &block)
  test([2, 2, 1, 4, 4, 3, 3], 1, &block)
  test([2, 2, 4, 4, 1, 3, 3], 1, &block)
  test([2, 2, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 1, 3, 3], 1, &block)
  test([1, 2, 2, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 3, 3, 9, 9], 1, &block)
  test([2, 2, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 3, 3, 9, 9, 1], 1, &block)
  test([8935, 8935, -1294, -1294, 822, 822, 6553, 6553, 5249, 5249, -7334, -7334, -9842, -9842, -9477, -9477, -4975, -4975, -7597, -7597, -9493, -9493, 881, 881, 9133, 9133, 6095, 6095, 6660, 6660, 2845, 2845, 7799, 7799, 5313, 5313, -4474, -4474, 6018, 6018, 3484, 3484, -3289, -3289, -1456, -1456, -3486, -3486, -7388, -7388, 8097, 8097, 8352, 8352, 1637, 1637, -6895, -6895, 3141, 3141, 6021, 6021, -5597, -5597, 9880, 9880, 5395, 5395, 3397, 3397, -750, -750, 4250, 4250, -3987, -3987, -717, -717, -9719, -9719, -8652, -8652, 3450, 3450, -7350, -7350, 2374, 2374, 1230, 1230, -7803, -7803, 5601, 5601, -1673, -1673, 9477, 9477, -5876, -5876, 2751, 2751, 2011, 2011, -7063, -7063, -8617, -8617, -9023, -9023, -5416, -5416, -5572, -5572, 4375, 4375, 4314, 4314, -8510, -8510, -3888, -3888, -8350, -8350, -201, -201, -3834, -3834, 237, 237, -830, -830, 147, 147, 3763, 3763, 5293, 5293, 4561, 4561, 1158, 1158, -8035, -8035, -1876, -1876, 3477, 3477, 252, 252, 8434, 8434, 4355, 4355, 4535, 4535, 3891, 3891, 1000, 1000, 2992, 2992, -6074, -6074, 2688, 2688, -1665, -1665, 4936, 4936, 7451, 7451, -3602, -3602, -7071, -7071, -6429, -6429, 6243, 6243, 5837, 5837, 828, 828, 6115, 6115, 8345, 8345, 2888, 2888, -8646, -8646, 484, 484, 9446, 9446, -1091, -1091, 3561, 3561, 7445, 7445, 9675, 9675, 9341, 9341, -1934, -1934, -5370, -5370, 9351, 9351, -6566, -6566, -5213, -5213, -4705, -4705, 297, 297, 2637, 2637, 3060, 3060, -8839, -8839, 1326, 1326, -9969, -9969, -7773, -7773, -1130, -1130, 6462, 6462, -8688, -8688, -3470, -3470, 5672, 5672, -4978, -4978, 9086, 9086, 4585, 4585, -6891, -6891, 6383, 6383, 9490, 9490, -4708, -4708, -274, -274, -4884, -4884, 711, 711, 4203, 4203, 9883, 9883, 4554, 4554, 4986, 4986, 2669, 2669, -5430, -5430, 2892, 2892, 6539, 6539, -1827, -1827, -976, -976, -4869, -4869, -7897, -7897, -9316, -9316, -5234, -5234, 1152, 1152, -3418, -3418, 3509, 3509, -2172, -2172, 658, 658, -6581, -6581, -3182, -3182, -1024, -1024, 2659, 2659, 8812, 8812, -8065, -8065, -7645, -7645, -9193, -9193, 5685, 5685, -2852, -2852, 6753, 6753, -7883, -7883, 419, 419, 5829, 5829, -1011, -1011, -3948, -3948, 4832, 4832, 9940, 9940, -7161, -7161, 3103, 3103, -8033, -8033, -2860, -2860, 6361, 6361, -6752, -6752, -8520, -8520, 8243, 8243, -7110, -7110, -9719, -9719, 349, 349, 6477, 6477, 6112,
        6112, 7816, 7816, -2242, -2242, 2389, 2389, 3415, 3415, 6189, 6189, -357, -357, 4849, 4849, -9404, -9404, 3148, 3148, -7017, -7017, 6830, 6830, -8142, -8142, -4914, -4914, -6558, -6558, -9783, -9783, -4125, -4125, -5793, -5793, 5735, 5735, 1483, 1483, 9847, 9847, -2883, -2883,
        -5990, -5990, -6637, -6637, 8459, 8459, 2916, 2916, 3218, 3218, -9332, -9332, -3525, -3525, 6243, 6243, 5487, 5487, 3649, 3649, 2414, 2414, -2856, -2856, 788, 788, 8196, 8196, -2313, -2313, -9646, -9646, -703, -703, -5241, -5241, -9189, -9189, -1345, -1345, 3486, 3486, 3154, 3154, -7894, -7894, -6216, -6216, 3889, 3889, -3166, -3166, 9287, 9287, -1510, -1510, 9541, 9541, 2576, 2576, 2191, 2191, -8348, -8348, 867,
        867, 1977, 1977, -8171, -8171, 8542, 8542, 5928, 5928, -4707, -4707, 9228, 9228, 7074, 7074, -651, -651, 5359, 5359, 4593, 4593, -8904, -8904, 5732, 5732, -3817, -3817, -5632, -5632, -8816, -8816, 4286, 4286, 7138, 7138, -6514, -6514, 8498, 8498, -4570, -4570, -7023, -7023, -9009, -9009, 763, 763, 8443, 8443, -6483, -6483, -234, -234, -1828, -1828, -1300, -1300, -5333, -5333, -7504, -7504, 60, 60, -6683, -6683, 1041, 1041, 703, 703, -4631, -4631, -9895, -9895, 6198, 6198, -5505, -5505, 1265, 1265, 7059, 7059, 9439, 9439, 6494, 6494, 4921, 4921, -2162, -2162, -9992, -9992, -4429, -4429, 290, 290, -8149, -8149, -5277, -5277, -9527, -9527, -169, -169, -647, -647, -2655, -2655, 2249, 2249,
        -6165, -6165, -8189, -8189, 8223, 8223, 6413, 6413, -779, -779, -6986, -6986, -9307, -9307, 8626, 8626, -4855, -4855, 8850, 8850, 6881, 6881, 5403, 5403, 2579, 2579, -4160, -4160, 2923, 2923, 2476, 2476, -6862, -6862, -6717, -6717, 4799, 4799, -4561, -4561, 8114, 8114, 3361, 3361, 7601, 7601, -6789, -6789, -3703, -3703, -958, -958, -8854, -8854, 7632, 7632, 6714, 6714, 5898, 5898, -5059, -5059, 2358, 2358, 7709, 7709, -8696, -8696, 2006, 2006, -3947, -3947, -589, -589, -8163, -8163, -3233, -3233, -1261, -1261, -767, -767, -8819, -8819, 1538, 1538, -4725, -4725, 4329, 4329, 8241, 8241, 6288, 6288, 5703, 5703, -3000, -3000, -2261, -2261, -5334, -5334, -5975, -5975, -1235, -1235, -8971, -8971, 4235, 4235, 3615, 3615, -7008, -7008, -7623, -7623, 5706, 5706, -2029, -2029, 1532, 1532, 188, 188, -4030, -4030, -732, -732, 9232, 9232, -759, -759, -4373, -4373, -4624, -4624, -583, -583, -6275, -6275, 9656, 9656, 1642, 1642, -6717, -6717, -3459, -3459, -3439, -3439, 2190, 2190, -7276, -7276, -4123, -4123, 7763, 7763, 1166, 1166, -8415, -8415, -866, -866, 8979, 8979, 6002, 6002, 3523, 3523, 5240, 5240, 8931, 8931, -177, -177, 3562, 3562, 4687, 4687, -8483, -8483, -181, -181, 2705, 2705, -6367, -6367, 3896, 3896, 3698, 3698, 8456, 8456, -8840,
        -8840, -9148, -9148, -3157, -3157, -9251, -9251, -3534, -3534, 3648, 3648, 9953, 9953, 3446, 3446, 6456, 6456, -2787, -2787, -751, -751, -2443, -2443, 4468, 4468, -5248, -5248, 1586, 1586, 2820, 2820, 5850, 5850, 3882, 3882, 5334, 5334, -5250, -5250, 5186, 5186, -7500, -7500, 570, 570, 6791, 6791, 7869, 7869, -6179, -6179, -7275, -7275, -6303, -6303, -705, -705, -7129, -7129, 5023, 5023, 1498, 1498, 9435, 9435, -2875, -2875, -498, -498, -8001, -8001, -5618, -5618, 5378, 5378, -2059, -2059, -7969, -7969, -9662, -9662, 3237, 3237, -4198, -4198, 9057, 9057, -6222, -6222, 4231, 4231, 688, 688, 4959, 4959, 2883, 2883, -8986, -8986, -6711, -6711, 1091, 1091, -6978, -6978, -9456, -9456, -3914, -3914, -1278, -1278, 7239, 7239, -5811, -5811, 7553, 7553, 3182, 3182, -3488, -3488, -3162, -3162, -6120, -6120, 9515, 9515, -7163, -7163, -1985, -1985, -9429, -9429, -1913, -1913, 1342, 1342, -418, -418, -8581, -8581, -9447, -9447, 1582, 1582, 4007, 4007, -3717, -3717, 219, 219, 9992, 9992, 7042, 7042, -9811, -9811, -3546, -3546, 8108, 8108, -2907, -2907, 2882, 2882, 7518, 7518, 1587, 1587, 4469, 4469, 4950, 4950, 7839, 7839, -4235, -4235, 5547, 5547, 4355, 4355, -4574, -4574, -6657, -6657, 1601, 1601, 9460, 9460, 6489, 6489, 8988, 8988, -2863, -2863, -2835, -2835, 173, 173, -5826, -5826, -2799, -2799, -5500, -5500, -3829, -3829, 3533, 3533, -6159, -6159, -6587, -6587, 9603, 9603, -5743, -5743, 3549, 3549, 5640, 5640, 3017, 3017, -124, -124, -5943, -5943, 5866, 5866, -4524, -4524, 3295, 3295, 1177, 1177, 2053, 2053, -7067, -7067, 1938, 1938, -3948, -3948, -9672, -9672, 7678, 7678, -7386, -7386, -6876, -6876, -7278, -7278, -4063, -4063, 2338, 2338, -7495, -7495, 7169, 7169, 3712, 3712, -8132, -8132, 3696, 3696, -4562, -4562, -2821, -2821, 5908, 5908, -2101, -2101, 2327, 2327, -2109, -2109, -7132, -7132, -3823, -3823, -2533, -2533, -862, -862, 9089, 9089, 4586, 4586, -5607, -5607, -3026, -3026, 2532, 2532, -3076, -3076, 5672, 5672, -9253, -9253, -4768, -4768, 5827, 5827, -8172, -8172, 6079, 6079, -6778, -6778, 9876, 9876, 5286, 5286, 2715, 2715, -8622, -8622, 6874, 6874, -6358, -6358, -3371, -3371, 6689, 6689, 9592, 9592, -1095, -1095, -7207, -7207, 2938, 2938, 9991, 9991, -9274, -9274, 7513, 7513, 8107, 8107, 7073, 7073, 5372, 5372, 4862, 4862, -9387, -9387, 1551, 1551, -9440, -9440, 133, 133, 3688, 3688, -9284, -9284, 5483, 5483, -596, -596, -9814, -9814, 3048, 3048, 776, 776, 9039, 9039, 2426, 2426, -6650, -6650, 5193, 5193, -8682, -8682, 7314, 7314, -2741, -2741, 9505, 9505, -4411, -4411, -9063, -9063, -1785, -1785, 1201, 1201, 1806, 1806, -537, -537, -3739, -3739, 4294, 4294, 9992, 9992, 9680, 9680, -8128, -8128, -9615, -9615, -7900, -7900, -8922, -8922, -7432, -7432, -4200, -4200, -4994, -4994, -5144, -5144, 2767, 2767, -1551, -1551, -9117, -9117, -9123, -9123, -2223, -2223, 8472, 8472, -6053, -6053, -2483, -2483, 7698, 7698, 9127, 9127, 2932, 2932, 3337, 3337, 8838, 8838, -6911, -6911, 2660, 2660, -6823, -6823, 491, 491, 7154, 7154, -7386, -7386, 4224, 4224, 6945, 6945, -9728, -9728, 1250, 1250, -3308, -3308, 4313, 4313, 8427, 8427, 5031, 5031, 8142, 8142, -3463, -3463, -7584, -7584, 8214, 8214, -8166, -8166, 4289, 4289, -1256, -1256, 7097, 7097, 9463, 9463, -9340, -9340, -239, -239, -7877, -7877, 2244, 2244, 9419, 9419, -7419, -7419, -4257, -4257, 8434, 8434, 3938, 3938, -4373, -4373, -8156, -8156, 9674, 9674, 8984, 8984, -7177, -7177, -7027, -7027, 5660, 5660, 2269, 2269, 2205, 2205, 9769, 9769, -6010, -6010, -6820, -6820, -5710, -5710, 8462, 8462, -9930, -9930, -5029, -5029, -3414, -3414, 6252, 6252, 4987, 4987, 1261, 1261, 3876, 3876, 4064, 4064, -3079, -3079, -4868, -4868, 9018, 9018, 5297, 5297, 2376, 2376, -7723, -7723, -1787, -1787, -4186, -4186, -2531, -2531, 6214, 6214, -770, -770, -893, -893, -1051, -1051, -138, -138, -7382, -7382, -3003, -3003, -7208, -7208, 8370, 8370, 4314, 4314, 8956, 8956, -6390, -6390, -2119, -2119, -1448, -1448, 1614, 1614, 2630, 2630, 8046, 8046, 4145, 4145, 3806, 3806, -8263, -8263, 6251, 6251, 4178, 4178, 3790, 3790, -5229, -5229,
        -7386, -7386, 3479, 3479, 7147, 7147, 2301, 2301, 8600, 8600, 9223, 9223, -7174, -7174, -4620, -4620, 9586, 9586, 5264, 5264, 63, 63, 6330, 6330, 7620, 7620, -4730, -4730, 4860, 4860, -3053, -3053, 8981, 8981, -5233, -5233, -6322, -6322, -1508, -1508, -4443, -4443, -5668, -5668, 9018, 9018, 428, 428, -9513, -9513, 4145, 4145, -5759, -5759, -5441, -5441, 3620, 3620, 1226, 1226, -7437, -7437, 631, 631, 900, 900, 7088, 7088, -5709, -5709, -9858, -9858, 8697, 8697, -5875, -5875, 7441, 7441, -1237, -1237, -3440, -3440, 2360, 2360, 3790, 3790, 7204, 7204,
        -3204, -3204, -1422, -1422, -3703, -3703, -1817, -1817, 786, 786, -420, -420, -176, -176, -727, -727, -2749, -2749, -2092, -2092, 487, 487, 1003, 1003, 9000, 9000, 5425, 5425, 6616, 6616, 2795, 2795, 2902, 2902, -8796, -8796, -2042, -2042, -8893, -8893, -3439, -3439, 6596, 6596, 4943, 4943, -7271, -7271, -1166, -1166, 6531, 6531, -2118, -2118, 2329, 2329, 798, 798, -774, -774, 8400, 8400, 1257, 1257, 5146, 5146, 7416, 7416, 2815, 2815, -2778, -2778, -5392, -5392, 2076, 2076, 6170, 6170, 1518, 1518, -4209, -4209, 1600, 1600, 8432, 8432, -2250, -2250,
        -7859, -7859, 7590, 7590, -1974, -1974, -440, -440, -3467, -3467, -796, -796, -6348, -6348, 2885, 2885, -4586, -4586, -4384, -4384, 8914, 8914, 9143, 9143, -9086, -9086, 7915, 7915, -5116, -5116, 9612, 9612, 1513, 1513, -2599, -2599, -2023, -2023, -4447, -4447, -9979, -9979, 2025, 2025, 2983, 2983, 8855, 8855, -6414, -6414, 5269, 5269, -6816, -6816, 5316, 5316, -9619, -9619, -5252, -5252, 5401, 5401, 4912, 4912, -3576, -3576, 8222, 8222, -3191, -3191, 7605, 7605, -4046, -4046, 4480, 4480, -5504, -5504, 1804, 1804, 3182, 3182, -4778, -4778, -4480, -4480, 2673, 2673, -9040, -9040, -3782, -3782, -8607, -8607, -7982, -7982, -4108, -4108, 4084, 4084, -1632, -1632, -183, -183, -4692, -4692, -4024, -4024, -4781, -4781, -7982, -7982, -9493, -9493, 2659, 2659, 8138, 8138, -2924, -2924, -9617, -9617, -4283, -4283, 1606, 1606, -230,
        -230, -7123, -7123, -4242, -4242, 8814, 8814, 4983, 4983, -8791, -8791, -2504, -2504, 1539, 1539, 5985, 5985, 5957, 5957, 1679, 1679, -8945, -8945, -6560, -6560, -9449, -9449, -7322, -7322, 6709, 6709, 695, 695, -9266, -9266, 8393, 8393, 4629, 4629, -3335, -3335, 7575, 7575, 1097, 1097, 2331, 2331, -3652, -3652, -7610, -7610, -3640, -3640, -9423, -9423, -4384, -4384, -1045, -1045, -9767, -9767, 3711, 3711, 619, 619, 3579, 3579, 5990, 5990, -9203, -9203, 368, 368, -3809, -3809, -5416, -5416, -1205, -1205, -767, -767, -8146, -8146, -525, -525, 4628, 4628, 4653, 4653, 1845, 1845, 4407, 4407, -834, -834, 9235, 9235, -8266, -8266, -5471, -5471, 6146, 6146, 3105, 3105, 1763, 1763, 8335, 8335, 2781, 2781, -5761, -5761, -617, -617, -3835, -3835, 5466, 5466, 8830, 8830, 2153, 2153, 8652, 8652, 5379, 5379, -2798, -2798, -9890, -9890, -1602, -1602, -3727, -3727, 5653, 5653, -7024, -7024, -5388, -5388, -281, -281, 6488, 6488, -8760, -8760, -5795, -5795, -2032, -2032, 9565, 9565, 9052, 9052, 1800, 1800, -6111, -6111, -1947, -1947, -4989, -4989, -7693, -7693, 3548, 3548, -6011, -6011, -3380, -3380, -4854, -4854, -5779, -5779, -8396, -8396, -1632, -1632, 7390, 7390, 1347, 1347, 7879, 7879, -5149, -5149, -6201, -6201, -1285, -1285, -6896, -6896, -8910, -8910, 7785, 7785, 6807, 6807, -2475, -2475, -6414, -6414, -9788, -9788, -6978, -6978, -4533, -4533, 4087, 4087, 7968, 7968, -538, -538, 4829, 4829, -6197, -6197, 3900, 3900, 2710, 2710, 2680, 2680, -4885, -4885, -6330, -6330, 1592, 1592, -8365, -8365, 6908, 6908, 9616, 9616, 9597, 9597, 4173, 4173, 8636, 8636, -1298, -1298, -7993, -7993, 2057, 2057, -1464, -1464, -1649, -1649, 739, 739, -2818, -2818, -6683, -6683, 5833, 5833, 8446, 8446, -5295, -5295, -588, -588, -5137, -5137, 6524, 6524, -9872, -9872, 6871, 6871, 8172, 8172, 9116, 9116, -2593, -2593, -9849, -9849, 2222, 2222, 1992, 1992, -4386, -4386, 6340, 6340, 6244, 6244, -174, -174, -8309, -8309, -5980, -5980, 10_001], 10_001, &block)

  puts 'all pass!'
end

def test(arr, expected)
  result = yield(arr)

  raise "Problem in test: #{arr}, expected value: #{expected}, but got #{result}" if expected != result
end

def time_comparing
  unique_value = 10_001
  arr_duplicates = create_array_of_duplicates
  arr_unique_at_last =  Array.new(arr_duplicates).push(unique_value)
  arr_unique_at_first = Array.new(arr_duplicates).unshift(unique_value)
  arr_unique_inside_1 = Array.new(arr_duplicates).insert(300, unique_value)
  arr_unique_inside_2 = Array.new(arr_duplicates).insert(3400, unique_value)
  arr_unique_inside_3 = Array.new(arr_duplicates).insert(2100, unique_value)

  puts 'linear'
  time_last_linear = Benchmark.measure { test(arr_unique_at_last, unique_value) { |n| linear_solution n } }.real
  puts 'log'
  time_last_log = Benchmark.measure { test(arr_unique_at_last, unique_value) { |n| log_solution n } }.real

  puts "element at last - linear: #{time_last_linear} - log: #{time_last_log} - linear/log #{time_last_linear / time_last_log}"

  puts 'linear'
  time_first_linear = Benchmark.measure { test(arr_unique_at_first, unique_value) { |n| linear_solution n } }.real
  puts 'log'
  time_first_log = Benchmark.measure { test(arr_unique_at_first, unique_value) { |n| log_solution n } }.real

  puts "element at first - linear: #{time_first_linear} - log: #{time_first_log} - linear/log #{time_first_linear / time_first_log}"

  puts 'linear'
  time_inside_linear = Benchmark.measure { test(arr_unique_inside_1, unique_value) { |n| linear_solution n } }.real
  puts 'log'
  time_inside_log = Benchmark.measure { test(arr_unique_inside_1, unique_value) { |n| log_solution n } }.real

  puts "element at inside - linear: #{time_inside_linear} - log: #{time_inside_log}  - linear/log #{time_inside_linear / time_inside_log}"

  puts 'linear'
  time_inside_linear = Benchmark.measure { test(arr_unique_inside_2, unique_value) { |n| linear_solution n } }.real
  puts 'log'
  time_inside_log = Benchmark.measure { test(arr_unique_inside_2, unique_value) { |n| log_solution n } }.real

  puts "element at inside - linear: #{time_inside_linear} - log: #{time_inside_log} - linear/log #{time_inside_linear / time_inside_log}"

  puts 'linear'
  time_inside_linear = Benchmark.measure { test(arr_unique_inside_3, unique_value) { |n| linear_solution n } }.real
  puts 'log'
  time_inside_log = Benchmark.measure { test(arr_unique_inside_3, unique_value) { |n| log_solution n } }.real

  puts "element at inside - linear: #{time_inside_linear} - log: #{time_inside_log} - linear/log #{time_inside_linear / time_inside_log}"
end

def create_array_of_duplicates
  arr = []

  5000.times do
    i = rand(-100_000..100_000)

    arr.push(i)
    arr.push(i)
  end

  arr
end

puts 'testing linear solution'
tests { |n| linear_solution(n) }

puts 'testing log solution'
tests { |n| log_solution(n) }

puts 'time comparing'
time_comparing