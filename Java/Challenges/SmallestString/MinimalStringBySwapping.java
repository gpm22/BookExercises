import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class MinimalStringBySwapping {

    private static int MAX_ALPHABET_SIZE = 26;

    public static void main(String[] args) {

        comparingTest("Already smaller", "aabbbee", "aabbbee");
        comparingTest("test different 1", "aaacccbbb", "aaabbbccc");
        comparingTest("test different 2", "cccabbb", "aaabccc");
        comparingTest("alphabet", "abcdefghijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz");
        comparingTest("reversed alphabet", "zyxwvutsrqponmlkjihgfedcba", "abcdefghijklmnopqrstuvwxyz");
        comparingTest("mixed alphabet", "tyxwvurqpongfedcbaszmlkjih", "abcdefghijklmnopqrstuvwxyz");
        comparingTest("big string 1",
                "kezcihtbxnsalyolqtzwqibcujlxxzamaxhjukqtjilooncuqksfzsbofggzttyqdmuzzrvwphcspopndysqabuewypynvauezujoeckihsvmlrdskrwvwyqzaqtourbuauuyqmwokbexbnnboficqbekobzirmdrsntcnalaneyqjnpnnhwuyqwxciutrcgnzilaqifetgwpoespkumgpbwwjtasrjhomtgdeweumcjggmypoqjetydplydwhixdcqkyixffytftfmxkumvnuqosjsptklfwpjndkvouweatzkvpbrkrhierirkvnxttbfebwfivmxueluqmiojiqqudphxgntomljqrcumoafzcxsellaaogaapcflezlsdgnracuamlvnhbbwfnxpiepbtlhwplxsejyhcqfhbrinocfwpukmpapabhbjsfoinorjuyfqdcbvzpegsgpczlrrvwdthtbuibnaftrmshoeobmhfqcoayjkhoyzfzyozipeurtikxcvjwykrtklwvvqrznoolnwdzmxifcxvjlurlgzawnrkfaoqnlggqrkvhthfaoklswowkwglmideloqysrushhrgrwixnzhcabuzsacgvguwllsznweufbbpqogtahoevurxudbbllumglnqjeflexcxmlmjzfkiuxnesebfttjwbpxswygiujionaanvkajquwlrorgpyjxiahkfmfpkouphxyrhsjnblgpdurxnhtxhewazjpoqfaglcpwtgjzefrsgdnxuszsgzoolumcucwjjjmlyodgezvthjhwknoxjhmysebnqbmylkajqnyozyizydsrqygadhjwrzgpwyehpnmikgnnjbrfqmphmhvstnlkuxbkftpkajhqptxvwaeqwfsdmbkrtthgdxihktejtfjsxiqujnbnqymtcvlavkzqkfdhzvyvpyawblccyamqgktakjzrxyelyfeaiqlagbtjqerbahyynhtotvukegnwekgnpiakrimihhaxkffypjpizaevtktdpzastxepqaapbxsmksqrxbynkeypevmgszbgmhpzxvazwbsmckdgmydfazpqqfvqkzhazxsbzxtcebdscroombjoniasmaoqolzwrhvqimuspnlqjoexvofwoxfsodziofdhlgaxobafjnivhjbpqoqxngpidhmhypirnbyuoffdnqjfabngescgeoknyxlvmrooqswhkoqfmvlyadbmqsnueshgdphftfgjnnscaoyyhswoyhakvmkwhjuawzqjsbjitliqxnrkmuitbgdtpozxexiixbdnurppyffixestmgvtdhkinjvreduptbnenipcqkeuqmfwpnjjvpzvbalqtxzsiguviajuzqgevqrvuauufkdfrzzmwxxkaxnyfofnhlmyodipmbnigfjbwcykgutlvgjohylwyllsdcuvueauwfyvforvlvvlcqdryvgcmjkvfhkswfqwzusdavabbciuywxvdgvzjqihcrzkvqrmjzwbmgpxfaganbtsdabybtzwsuztqtpumpervefuyewqccsmthsacvzxyjqvoiepqwxbtafxyplfbluraglefwydstlxhksodztuwkfogzukpffentwlquidnlqwfhrgncsalsnberqaebqypwgqxvnoavnvfoovicadzmexraiaclqaqygqhptjvspmpterjndqyzkrfdwznkjtawurmsbidjsgqxbcojewzehltqnzmmzvgagbgtdrpbzsnrnfndhwagfrehqakpzieaifebfddhkihfqorkpmwwrrnzgzqljjqirlakhkzevootbrzhtwruplkhdgtwsrzelegshtcrpmspmforgqikywtmkqfdiggzokrvunsqqxlzpluxbsgvviudfvnnevfbtwdkvcldkoauxidqabuyegigsdamqfdlrblydbbtwfhujcqjcddeslinbpgjvceneualywskuhpolmsgioorpqiaqbkwdfzgchlwzltarnzkmrzuhtgzpwutgtktqnaoxasfpdrxispvtychazcnexptsrvllmwfnxgkwfsccnnolgydpdxuadwhxusotchdwdrdykpystpbkxhwblcwwgifaiyksdyslywonugiqoswoumhxzgizgpoiuselcyjjfenoqssqgthlgvvgvjxsvzfbtyycarhtrpwicrlcwcwszscitthdypwdhbkfdycdlqlthblhmuzklinsmiebxwbmdulquqqvsocnriiejxmuapwyzpyjofxljyhmusvteqpwswitfmrnjqwretbckshahwwlpjowvstwnorakmfdzawfricnhdzzvmojrswtbylewsvpanayoxemcqmeqookfjhyggynuglteihosdxtrnqtscjfyiwgkoknrizpnbwclqdyhtoycrphydfhijnenvtnzodyuinwqriizsncsvqpooxcldaptplzlzimmhanblojrqzltekwarogpzjmpiodewswpxzonqjtsjjhutwxjxobzqwhhdbkvxksgwbmdwqvfekdcwvgrkpvuhfwcinvnxxoveyjgshpvwtchpqpkiqonbwykayoruipdimexghipwvapwidvszppzvetmqfnkljqacapslttoxxvsgocpnfjljqgrlgptssdezjzancadydkpctkvwpygbnhvtngdqagxtvapajovjaazbfrpnvpibcfmsscyhehsnuhsmvsfpxpnbwdadjuxpbzepyvmfpfpyaelvodhkxaentmtpeyyuwrkgduqklrxurbzuzbaeradjeebubkeydweyehektppqbqqntkcdanuspnmygcbkorkzxjwkpmfusrzbwglkplcuuwcyhyaftqjnkgxxlnuchqvgszslfotwpihukngrhflrbgbufjvywohzcoiunkxgynzxdvjfgdusioqmzhohobbzpzmzuqnzqkdlgjdhpybkgowhxpexwmhqsmmytyiljvpucmqewpcipxzuctrppwfbukooikxxbozrudmmnldhnbxdwudyxicwozmxvyrqjynpszgfjlamtvtoberqacyjahgncmvgrvorpxtzdlpmjajpoiznhaazrpwbywlwcbsuaboogccueegtobmuu",
                "abcdefghijklmnompgcqpehdrsmiicltlifsrapgsemoojdrpakuckhouvvcggnpwtrccxyqzfdkzozjwnkplhrbqnznjylrbcrsobdaefkytmxwkaxqyqnpclpgorxhrlrrnptqoahbihjjhouedphbaohcextwxkjgdjlmljbnpsjzjjfqrnpqidergxdvjcemlpeubgvqzobkzartvzhqqsglkxsfotgvwbqbrtdsvvtnzopsbgnwzmnwqfeiwdpaneiuungugutiartyjrpokskzgamuqzsjwayorqblgcayzhxaxfebxexayjigghubhqueytirbmrpteosepprwzfivjgotmspxdrtolucdikbmmllovllzdumbcmkwvjxldrltmyjfhhqujizebzhgmfqzmikbsnfdpufhxejoduqzratzlzlhfhskuoejoxsrnupwdhyczbvkvzdcmxxyqwgfghrehjlugxtkfobohtfupdolnsafoncucnocezbrxgeaidysqnaxgamqyypxcjoomjqwctieudiysmrxmvclqjxaulopjmvvpxayfgfuloamkqoqaqvmtewbmopnkxrkffxvxqeijcfdlhrckldvyvrqmmkcjqbruhhzpovglfobyrxirwhhmmrtvmjpsbumbiditmtscuaerijbkbhuggsqhzikqnverseojlljyalsprqmxoxvznsielfautuzaorzfinxfksjhmvzwrxijfgifbqlcszopulvmdzqgvscbuxkvwjirkckvcoomrtdrdqssstmnowvbcygfsfqajoisftnkbhjphtnmalspjnocnecnwkxpnvlwfsqxcvzqnbfzjteavjjshxuptzftfykgjmarihaugzalsfpzgiyqlbpqukwthaxggfvwiefagbsguskieprsjhjpntgdymlyacpauwfcynyznlqhmddnltpvaglascxinbmnublepmlvhgspbxhlfnnjfgogyrabvjqbavjzelaxeteffliauunzszeclbygagwzclkgibzpllzhiktakpxihnjabnzbytvkchvtfzciylcqhktdawvtnwulczppuypacflcikhcigdbhwkdxoothsojelktlopomcqxfypetrkzjmpsobiyouqoiukowceouwfmvliohlusjeyfshzpopijvzewftfnzexjhnrouuwjpsulhjvbkdvboajnimytxoopkqfaoputymnlwhtpkjrbkfvwzfuguvsjjkdlonnfkqonflaytaqfsrlqcpskhsegmepijxatreghvwgzocibieeihwjrxzznuueibkgtvygwfaejsyxbwrzghjbjezdpabrptuqzjssyzcyhlmpgickevryelsrcpvbypxyrlrruawuxcctqiialijnuoujfmtnowezthjevushqdnavrgmyvsofnmqnmmkwdryrblrqunyuoxymyymdpwxnyvdtsayufakqupqcrkwlylhhdernqiywvycspefdxcaypxtscqhtvziulvljhgkwlhnhgcqkrcgpgzrtzbxyburnbqpddktgfkldycinspyoebzpqihgluinzmuhmrxlvmbuqnwkgmifakowcgrqauovcrazuubjgqmprewjmpqufxvjdklmkjhbxplbhpnzqvpiyjolyjyuooyedlwctbixleldmplpnvpfzgsykztzgbxsjwpncaxuwqcjasglqrxtkhewskvpihdosbqcbfmgpjcttcyvlvhvgwxzhckjxjujwfqlvuxbfplazcebleubhuwwfaefupoxaztqqxxjcvcpmsspexmlafacbyooghxcfgqxrzmafwvgqkxcbmbvkfgdxztkztuoxvpeanqgtapuwevvcoaxyrjkppimczmrihkvyyerwuyjjbyuhgqwaydmwaolriewplhrnbvevkwltpuwmxhmnwhhgqufrsdpsdwwbkmejhzvsydbjbrlmnqkarfzomtkveooxzpelphaqwucvdfmqcmglxjcatxcrfgvczqrgvgagpjloilkuzwxiekzygndflcdjbizgkxymmtqujivaqukddjjomvnwzwirlwqfirkogdfwqwxwnaznkgzhaifqhmdqqveulenakwnkmnqojrvepokqortficvecvzoerkbmdnssubjopkkpvgfmvyyvysikycuhgnndlxfgxzqedxmdqdqkckdeggfwnzqwfhauwndwmpmgfhmftrcamejktebhiqhtwrmprppykodjxeebsitrlzqncznsouimsnftrkygbpzqkqegutxjspqxbghdakflfqqmzsoqykgqjoxlatuwclquxedjfwccytosxkqghnmbqkyzljlnoibtdptbpooausfnvvnjrvmgbefokwigxjpgkdsuneqvaoajxeczjhqdmpwnfgondxzfnwufesjbjygjcownrejqpxeeckjdkypzooidmwlzgzmcmcettfljhmosxpcmgbaqlxovzcstzeowbqkqzicojpsgkssfrgqisiohcpqffwhayiakvqhtwqpyubawdqyvxazyrfuqdejyjiioybnsvkfzyqgdfzpzaepojhqnalnoxrezwetbivfezqylzqewykczzcybgtpujamspldlzkmggoiiykvodzjusmspvxmvzgkkwbcscljdlwnwazdgayqznvhjfygjvwplvigylzlsoysllchuxzjyzehdutkkdnfbfkjrfktykuzizjhqwlwsrizhcbznytuzuznlbmyowfailbjgtgzbnnrqxavwrpamxirxhcrchlbxlwsbbhrhabnwqbnbfbagzzphppjgadwljrkzjtnvdhaoxacisqazturkxchqvmazmdrrqdnfnlugpsjaviimjrdfpyvkckmuogqzefrajvxfumxhvhrusynqofcdoerjaivnjciwysuvwrkeoptcfofohhczctcrpjcpawmvswfznhavoqfizbiqtfpkttngnemsyzrdtpbqzdezicrdgxzzquhraooeaiihocxrwttjmwfjhiwqrwniedqoctiynxpsnjzkcvusmltgygohbxpldnslfvjdtyvxyoxzigcwmztslszoecjfllcxzqhnqmqdhkrlhoovddrbbvgohtrr");
        comparingTest("big string 2",
                "kkezcihtbxnsalyolqtzwqibcujlxxzamaxhjukqtjilooncuqksfzsbofggzttyqdmuzzrvwphcspopndysqabuewypynvauezujoeckihsvmlrdskrwvwyqzaqtourbuauuyqmwokbexbnnboficqbekobzirmdrsntcnalaneyqjnpnnhwuyqwxciutrcgnzilaqifetgwpoespkumgpbwwjtasrjhomtgdeweumcjggmypoqjetydplydwhixdcqkyixffytftfmxkumvnuqosjsptklfwpjndkvouweatzkvpbrkrhierirkvnxttbfebwfivmxueluqmiojiqqudphxgntomljqrcumoafzcxsellaaogaapcflezlsdgnracuamlvnhbbwfnxpiepbtlhwplxsejyhcqfhbrinocfwpukmpapabhbjsfoinorjuyfqdcbvzpegsgpczlrrvwdthtbuibnaftrmshoeobmhfqcoayjkhoyzfzyozipeurtikxcvjwykrtklwvvqrznoolnwdzmxifcxvjlurlgzawnrkfaoqnlggqrkvhthfaoklswowkwglmideloqysrushhrgrwixnzhcabuzsacgvguwllsznweufbbpqogtahoevurxudbbllumglnqjeflexcxmlmjzfkiuxnesebfttjwbpxswygiujionaanvkajquwlrorgpyjxiahkfmfpkouphxyrhsjnblgpdurxnhtxhewazjpoqfaglcpwtgjzefrsgdnxuszsgzoolumcucwjjjmlyodgezvthjhwknoxjhmysebnqbmylkajqnyozyizydsrqygadhjwrzgpwyehpnmikgnnjbrfqmphmhvstnlkuxbkftpkajhqptxvwaeqwfsdmbkrtthgdxihktejtfjsxiqujnbnqymtcvlavkzqkfdhzvyvpyawblccyamqgktakjzrxyelyfeaiqlagbtjqerbahyynhtotvukegnwekgnpiakrimihhaxkffypjpizaevtktdpzastxepqaapbxsmksqrxbynkeypevmgszbgmhpzxvazwbsmckdgmydfazpqqfvqkzhazxsbzxtcebdscroombjoniasmaoqolzwrhvqimuspnlqjoexvofwoxfsodziofdhlgaxobafjnivhjbpqoqxngpidhmhypirnbyuoffdnqjfabngescgeoknyxlvmrooqswhkoqfmvlyadbmqsnueshgdphftfgjnnscaoyyhswoyhakvmkwhjuawzqjsbjitliqxnrkmuitbgdtpozxexiixbdnurppyffixestmgvtdhkinjvreduptbnenipcqkeuqmfwpnjjvpzvbalqtxzsiguviajuzqgevqrvuauufkdfrzzmwxxkaxnyfofnhlmyodipmbnigfjbwcykgutlvgjohylwyllsdcuvueauwfyvforvlvvlcqdryvgcmjkvfhkswfqwzusdavabbciuywxvdgvzjqihcrzkvqrmjzwbmgpxfaganbtsdabybtzwsuztqtpumpervefuyewqccsmthsacvzxyjqvoiepqwxbtafxyplfbluraglefwydstlxhksodztuwkfogzukpffentwlquidnlqwfhrgncsalsnberqaebqypwgqxvnoavnvfoovicadzmexraiaclqaqygqhptjvspmpterjndqyzkrfdwznkjtawurmsbidjsgqxbcojewzehltqnzmmzvgagbgtdrpbzsnrnfndhwagfrehqakpzieaifebfddhkihfqorkpmwwrrnzgzqljjqirlakhkzevootbrzhtwruplkhdgtwsrzelegshtcrpmspmforgqikywtmkqfdiggzokrvunsqqxlzpluxbsgvviudfvnnevfbtwdkvcldkoauxidqabuyegigsdamqfdlrblydbbtwfhujcqjcddeslinbpgjvceneualywskuhpolmsgioorpqiaqbkwdfzgchlwzltarnzkmrzuhtgzpwutgtktqnaoxasfpdrxispvtychazcnexptsrvllmwfnxgkwfsccnnolgydpdxuadwhxusotchdwdrdykpystpbkxhwblcwwgifaiyksdyslywonugiqoswoumhxzgizgpoiuselcyjjfenoqssqgthlgvvgvjxsvzfbtyycarhtrpwicrlcwcwszscitthdypwdhbkfdycdlqlthblhmuzklinsmiebxwbmdulquqqvsocnriiejxmuapwyzpyjofxljyhmusvteqpwswitfmrnjqwretbckshahwwlpjowvstwnorakmfdzawfricnhdzzvmojrswtbylewsvpanayoxemcqmeqookfjhyggynuglteihosdxtrnqtscjfyiwgkoknrizpnbwclqdyhtoycrphydfhijnenvtnzodyuinwqriizsncsvqpooxcldaptplzlzimmhanblojrqzltekwarogpzjmpiodewswpxzonqjtsjjhutwxjxobzqwhhdbkvxksgwbmdwqvfekdcwvgrkpvuhfwcinvnxxoveyjgshpvwtchpqpkiqonbwykayoruipdimexghipwvapwidvszppzvetmqfnkljqacapslttoxxvsgocpnfjljqgrlgptssdezjzancadydkpctkvwpygbnhvtngdqagxtvapajovjaazbfrpnvpibcfmsscyhehsnuhsmvsfpxpnbwdadjuxpbzepyvmfpfpyaelvodhkxaentmtpeyyuwrkgduqklrxurbzuzbaeradjeebubkeydweyehektppqbqqntkcdanuspnmygcbkorkzxjwkpmfusrzbwglkplcuuwcyhyaftqjnkgxxlnuchqvgszslfotwpihukngrhflrbgbufjvywohzcoiunkxgynzxdvjfgdusioqmzhohobbzpzmzuqnzqkdlgjdhpybkgowhxpexwmhqsmmytyiljvpucmqewpcipxzuctrppwfbukooikxxbozrudmmnldhnbxdwudyxicwozmxvyrqjynpszgfjlamtvtoberqacyjahgncmvgrvorpxtzdlpmjajpoiznhaazrpwbywlwcbsuaboogccueegtobmuu",
                "aabcdefghijklmnompgcqpehdrsmiicltlifsrapgsemoojdrpakuckhouvvcggnpwtrccxyqzfdkzozjwnkplhrbqnznjylrbcrsobdaefkytmxwkaxqyqnpclpgorxhrlrrnptqoahbihjjhouedphbaohcextwxkjgdjlmljbnpsjzjjfqrnpqidergxdvjcemlpeubgvqzobkzartvzhqqsglkxsfotgvwbqbrtdsvvtnzopsbgnwzmnwqfeiwdpaneiuungugutiartyjrpokskzgamuqzsjwayorqblgcayzhxaxfebxexayjigghubhqueytirbmrpteosepprwzfivjgotmspxdrtolucdikbmmllovllzdumbcmkwvjxldrltmyjfhhqujizebzhgmfqzmikbsnfdpufhxejoduqzratzlzlhfhskuoejoxsrnupwdhyczbvkvzdcmxxyqwgfghrehjlugxtkfobohtfupdolnsafoncucnocezbrxgeaidysqnaxgamqyypxcjoomjqwctieudiysmrxmvclqjxaulopjmvvpxayfgfuloamkqoqaqvmtewbmopnkxrkffxvxqeijcfdlhrckldvyvrqmmkcjqbruhhzpovglfobyrxirwhhmmrtvmjpsbumbiditmtscuaerijbkbhuggsqhzikqnverseojlljyalsprqmxoxvznsielfautuzaorzfinxfksjhmvzwrxijfgifbqlcszopulvmdzqgvscbuxkvwjirkckvcoomrtdrdqssstmnowvbcygfsfqajoisftnkbhjphtnmalspjnocnecnwkxpnvlwfsqxcvzqnbfzjteavjjshxuptzftfykgjmarihaugzalsfpzgiyqlbpqukwthaxggfvwiefagbsguskieprsjhjpntgdymlyacpauwfcynyznlqhmddnltpvaglascxinbmnublepmlvhgspbxhlfnnjfgogyrabvjqbavjzelaxeteffliauunzszeclbygagwzclkgibzpllzhiktakpxihnjabnzbytvkchvtfzciylcqhktdawvtnwulczppuypacflcikhcigdbhwkdxoothsojelktlopomcqxfypetrkzjmpsobiyouqoiukowceouwfmvliohlusjeyfshzpopijvzewftfnzexjhnrouuwjpsulhjvbkdvboajnimytxoopkqfaoputymnlwhtpkjrbkfvwzfuguvsjjkdlonnfkqonflaytaqfsrlqcpskhsegmepijxatreghvwgzocibieeihwjrxzznuueibkgtvygwfaejsyxbwrzghjbjezdpabrptuqzjssyzcyhlmpgickevryelsrcpvbypxyrlrruawuxcctqiialijnuoujfmtnowezthjevushqdnavrgmyvsofnmqnmmkwdryrblrqunyuoxymyymdpwxnyvdtsayufakqupqcrkwlylhhdernqiywvycspefdxcaypxtscqhtvziulvljhgkwlhnhgcqkrcgpgzrtzbxyburnbqpddktgfkldycinspyoebzpqihgluinzmuhmrxlvmbuqnwkgmifakowcgrqauovcrazuubjgqmprewjmpqufxvjdklmkjhbxplbhpnzqvpiyjolyjyuooyedlwctbixleldmplpnvpfzgsykztzgbxsjwpncaxuwqcjasglqrxtkhewskvpihdosbqcbfmgpjcttcyvlvhvgwxzhckjxjujwfqlvuxbfplazcebleubhuwwfaefupoxaztqqxxjcvcpmsspexmlafacbyooghxcfgqxrzmafwvgqkxcbmbvkfgdxztkztuoxvpeanqgtapuwevvcoaxyrjkppimczmrihkvyyerwuyjjbyuhgqwaydmwaolriewplhrnbvevkwltpuwmxhmnwhhgqufrsdpsdwwbkmejhzvsydbjbrlmnqkarfzomtkveooxzpelphaqwucvdfmqcmglxjcatxcrfgvczqrgvgagpjloilkuzwxiekzygndflcdjbizgkxymmtqujivaqukddjjomvnwzwirlwqfirkogdfwqwxwnaznkgzhaifqhmdqqveulenakwnkmnqojrvepokqortficvecvzoerkbmdnssubjopkkpvgfmvyyvysikycuhgnndlxfgxzqedxmdqdqkckdeggfwnzqwfhauwndwmpmgfhmftrcamejktebhiqhtwrmprppykodjxeebsitrlzqncznsouimsnftrkygbpzqkqegutxjspqxbghdakflfqqmzsoqykgqjoxlatuwclquxedjfwccytosxkqghnmbqkyzljlnoibtdptbpooausfnvvnjrvmgbefokwigxjpgkdsuneqvaoajxeczjhqdmpwnfgondxzfnwufesjbjygjcownrejqpxeeckjdkypzooidmwlzgzmcmcettfljhmosxpcmgbaqlxovzcstzeowbqkqzicojpsgkssfrgqisiohcpqffwhayiakvqhtwqpyubawdqyvxazyrfuqdejyjiioybnsvkfzyqgdfzpzaepojhqnalnoxrezwetbivfezqylzqewykczzcybgtpujamspldlzkmggoiiykvodzjusmspvxmvzgkkwbcscljdlwnwazdgayqznvhjfygjvwplvigylzlsoysllchuxzjyzehdutkkdnfbfkjrfktykuzizjhqwlwsrizhcbznytuzuznlbmyowfailbjgtgzbnnrqxavwrpamxirxhcrchlbxlwsbbhrhabnwqbnbfbagzzphppjgadwljrkzjtnvdhaoxacisqazturkxchqvmazmdrrqdnfnlugpsjaviimjrdfpyvkckmuogqzefrajvxfumxhvhrusynqofcdoerjaivnjciwysuvwrkeoptcfofohhczctcrpjcpawmvswfznhavoqfizbiqtfpkttngnemsyzrdtpbqzdezicrdgxzzquhraooeaiihocxrwttjmwfjhiwqrwniedqoctiynxpsnjzkcvusmltgygohbxpldnslfvjdtyvxyoxzigcwmztslszoecjfllcxzqhnqmqdhkrlhoovddrbbvgohtrr");
        String bigReversedAlphabet = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqppppppppppppppppppppppppppppppppppppppppppppppppppppppooooooooooooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmlllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiihhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeedddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddcccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        String correctBigReversedString = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccdddddddddddddddddddddddddddddddddddddddddddddddeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeffffffffffffffffffffffffffffffffffffffffffffffffffffgggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiijjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkklllllllllllllllllllllllllllllllllllllllllllllllllllllmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooppppppppppppppppppppppppppppppppppppppppppppppppqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
        comparingTest("big reversed alphabet",
                bigReversedAlphabet,
                correctBigReversedString);
    }

    private static void comparingTest(String name, String str, String expected) {
        long quadratic = testQuadratic(name, str, expected);
        long linearOnAlphabet = testLinearOnAlphabet(name, str, expected);
        long linear = testLinear(name, str, expected);
        System.out.println("linear on alphabet/quadratic %" + ((linearOnAlphabet * 100.0) / quadratic));
        System.out.println("linear/linear on alphabet %" + ((linear * 100.0) / linearOnAlphabet));
        System.out.println("linear/quadratic %" + ((linear * 100.0) / quadratic));
    }

    private static long testQuadratic(String name, String str, String expected) {
        long initTime = System.nanoTime();
        String ans = getMinimalStringQuadratic(str);
        long endTime = System.nanoTime();
        testMessage("Quadratic " + name, str, expected, ans, (endTime - initTime));
        return endTime - initTime;
    }

    private static long testLinearOnAlphabet(String name, String str, String expected) {
        long initTime = System.nanoTime();
        String ans = getMinimalStringLinearOnAlphabet(str);
        long endTime = System.nanoTime();
        testMessage("Linear On Alphabet " + name, str, expected, ans, (endTime - initTime));
        return endTime - initTime;
    }

    private static long testLinear(String name, String str, String expected) {
        long initTime = System.nanoTime();
        String ans = getMinimalStringLinear(str);
        long endTime = System.nanoTime();
        testMessage("Linear " + name, str, expected, ans, (endTime - initTime));
        return endTime - initTime;
    }

    private static void testMessage(String name, String str, String expected, String ans, long time) {
        boolean correct = expected.equals(ans);
        System.out.println("Test " + name + " - time: " + time + "\n\tCorrect? "
                + (correct ? "yes :D" : "no :("));

        if (!correct)
            throw new RuntimeException(
                    "\tIt is - \n" + ans + "\n\tBut should be - \n" + expected + "\n\toriginal - \n" + str);
    }

    // Algorithm is O(m^2*n), where n is the string length and m is the alphabet
    // length
    public static String getMinimalStringQuadratic(String str) {

        List<Character> list = getListOfSortedUniqueChars(str);

        String temp = str;
        String ans = swapIfPossible(str, list);

        while (ans != temp) {
            temp = ans;
            ans = swapIfPossible(ans, list);
        }

        return ans;
    }

    // Algorithm is O(m log m + n*m), where n is the string length and m is the
    // alphabet length, in the case n >> m, it is O(n*m)
    public static String getMinimalStringLinearOnAlphabet(String str) {
        Map<Character, Integer> map = mapCorrectOrder(str);
        String ans = str;

        for (Entry<Character, Integer> entry : map.entrySet()) {
            char newChar = entry.getKey();
            char oldChar = ans.charAt(entry.getValue());
            if (newChar < oldChar)
                ans = swap(ans, newChar, oldChar);
        }

        return ans;

    }

    // Algorithm is O(m log m + n), where n is the string length and m is the
    // alphabet length, in the case n >> m, it is O(n)
    public static String getMinimalStringLinear(String str) {
        Map<Character, Character> mapChars = mapOldToNewChar(str);
        return getSwappedString(str, mapChars);
    }

    private static Map<Character, Character> mapOldToNewChar(String str) {
        return mapCorrectOrder(str)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> str.charAt(e.getValue()),
                        Entry::getKey));
    }

    private static Map<Character, Integer> mapCorrectOrder(String str) {
        return mapCorrectOrder(mapCharPos(str));
    }

    private static Map<Character, Integer> mapCorrectOrder(Map<Character, Integer> mapCharPos) {
        List<Character> sortedKeys = getSortedKeys(mapCharPos);
        List<Integer> sortedValues = getSortedValues(mapCharPos);

        return IntStream.range(0, sortedKeys.size()).boxed()
                .collect(Collectors.toMap(sortedKeys::get, sortedValues::get));
    }

    private static Map<Character, Integer> mapCharPos(String str) {
        char[] chars = str.toCharArray();
        Map<Character, Integer> map = new HashMap<>();

        int i = 0;
        while (map.size() <= MAX_ALPHABET_SIZE && i < chars.length) {
            if (!map.containsKey(chars[i]))
                map.put(chars[i], i);

            i++;
        }

        return map;

    }

    private static String getSwappedString(String str, Map<Character, Character> mapOldToNew) {
        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++)
            chars[i] = mapOldToNew.get(chars[i]);

        return String.valueOf(chars);
    }

    private static List<Integer> getSortedValues(Map<Character, Integer> map) {
        return map.values().stream().sorted().toList();
    }

    private static List<Character> getSortedKeys(Map<Character, Integer> map) {
        return map.keySet().stream().sorted().toList();
    }

    private static List<Character> getListOfSortedUniqueChars(String str) {
        return str.chars().distinct().sorted().mapToObj(e -> (char) e).toList();
    }

    private static String swapIfPossible(String str, List<Character> list) {

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                char smallerChar = list.get(i);
                char largerChar = list.get(j);
                int smallerCharPos = str.indexOf(smallerChar);
                int largerCharPos = str.indexOf(largerChar);
                if (largerCharPos < smallerCharPos)
                    return swap(str, smallerChar, largerChar);
            }
        }
        return str;
    }

    private static String swap(String str, char ch1, char ch2) {
        char tempChar = '0';
        return str.replace(ch1, tempChar)
                .replace(ch2, ch1)
                .replace(tempChar, ch2);
    }

}
