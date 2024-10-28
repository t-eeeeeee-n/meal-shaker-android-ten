package com.example.frontend.utils

import com.example.frontend.data.models.Range

object Constants {
    val rangeOptions = listOf(
        Range("1", "100m"),
        Range("2", "500m"),
        Range("3", "1km"),
        Range("4", "5km")
    )
//    val genreOptions = listOf("居酒屋", "和食", "洋食", "中華", "イタリアン", "フレンチ")
//    val largeServiceAreaOptions = listOf(
//        "-", "関東", "関西", "東海", "北海道", "東北", "北陸・甲信越", "中国", "四国", "九州・沖縄"
//    )
//    val largeAreaOptions: Map<String, List<String>> = mapOf(
//        "-" to listOf("-"),
//        "関東" to listOf("東京", "神奈川", "埼玉", "千葉", "茨城", "栃木", "群馬"),
//        "関西" to listOf("大阪", "京都", "兵庫", "奈良", "和歌山", "滋賀"),
//        "東海" to listOf("愛知", "岐阜", "三重", "静岡"),
//        "北海道" to listOf("北海道"),
//        "東北" to listOf("青森", "岩手", "宮城", "秋田", "山形", "福島"),
//        "北陸・甲信越" to listOf("新潟", "富山", "石川", "福井", "山梨", "長野"),
//        "中国" to listOf("鳥取", "島根", "岡山", "広島", "山口"),
//        "四国" to listOf("徳島", "香川", "愛媛", "高知"),
//        "九州・沖縄" to listOf("福岡", "佐賀", "長崎", "熊本", "大分", "宮崎", "鹿児島", "沖縄")
//    )
//    val middleAreaOptions: Map<String, List<String>> = mapOf(
//        "-" to listOf("-"),
//        "東京" to listOf("銀座・有楽町・新橋・築地・月島", "水道橋・飯田橋・神楽坂", "お台場", "東京・大手町・日本橋・人形町", "四ツ谷・麹町・市ヶ谷・九段下", "上野・御徒町・浅草", "北千住・日暮里・葛飾・荒川"),
//        "神奈川" to listOf("横浜", "桜木町みなとみらい・関内・中華街", "溝の口・たまプラーザ・青葉台", "上大岡・新杉田・金沢文庫", "川崎・鶴見", "本厚木・海老名", "鎌倉・江の島"),
//        "埼玉" to listOf("大宮・さいたま新都心", "川口・西川口・蕨", "浦和・武蔵浦和", "熊谷・深谷・本庄", "川越", "秩父"),
//        "千葉" to listOf("千葉・稲毛", "船橋・津田沼・市川", "柏・南柏・我孫子", "成田・佐倉", "木更津・市原"),
//        "茨城" to listOf("水戸", "つくば", "日立・ひたちなか", "土浦", "鹿嶋・鉾田"),
//        "栃木" to listOf("宇都宮", "日光・鹿沼", "那須・塩原", "足利・佐野"),
//        "群馬" to listOf("前橋", "高崎", "草津・伊香保", "桐生市・みどり市", "館林・大泉町"),
//        "滋賀" to listOf("滋賀県その他", "草津市・守山市", "大津", "長浜市・彦根市", "近江八幡市・東近江市"),
//        "京都" to listOf("烏丸御池・四条烏丸", "左京区・山科区", "北区・上京区", "河原町・木屋町", "祇園・先斗町", "四条大宮・西院・右京区・西京区", "烏丸五条・京都駅周辺", "伏見桃山・伏見区・京都市郊外", "宮津・京丹後・京都府その他"),
//        "大阪" to listOf("梅田", "福島・野田", "淀屋橋・本町・北浜・天満橋", "京橋・天満・天六・南森町", "心斎橋・なんば・南船場・堀江", "天王寺", "上本町・鶴橋", "針中野・長居・西田辺・西成区・住吉", "関目・千林・緑橋・深江橋", "大阪市その他", "堺・高石市・和泉市", "高槻", "茨木", "泉大津・岸和田・泉佐野・りんくう", "松原市・藤井寺市・富田林・南河内", "江坂・西中島・新大阪・十三", "東大阪市・八尾市・平野・大東市", "九条・西九条・弁天町・大正・住之江", "枚方・寝屋川・守口・門真", "大阪府その他", "箕面・池田"),
//        "兵庫" to listOf("神戸", "明石", "灘・東灘", "須磨・垂水・西区・兵庫・長田", "姫路", "加古川", "淡路島", "西宮・芦屋・宝塚", "城崎・豊岡", "北区・有馬温泉・三田", "尼崎", "兵庫県その他"),
//        "奈良" to listOf("奈良県その他", "奈良市", "天理市・橿原市", "大和郡山市・生駒市"),
//        "和歌山" to listOf("白浜・和歌山県その他", "和歌山市", "田辺市"),
//        "岐阜" to listOf("岐阜駅周辺・柳ヶ瀬・市役所", "島・則武・正木・長良", "県庁～岐南・柳津・岐阜駅以南", "穂積・北方・大垣", "日野・芥見・各務原", "高山・中津川", "多治見・土岐", "下呂・岐阜県その他"),
//        "静岡" to listOf("静岡駅周辺・葵区・駿河区", "清水駅周辺～草薙", "浜松", "伊豆・伊東・下田", "焼津・藤枝・掛川", "熱海", "富士宮", "静岡県その他", "御殿場・富士・沼津・三島"),
//        "愛知" to listOf("名古屋（名古屋駅/西区/中村区）", "栄ｷﾀ錦/伏見丸の内/泉/東桜/新栄", "栄(ミナミ)/矢場町/大須/上前津", "金山・神宮前・熱田区", "鶴舞・八事・御器所", "大曽根･千種･今池･池下･守山区", "緑区・南区・天白区・瑞穂区", "本山・覚王山・藤が丘", "豊田市", "中川区・港区", "名古屋市その他", "安城・刈谷・岡崎・知立・蒲郡", "半田・知多・碧南・西尾", "春日井・小牧・一宮・江南・瀬戸", "豊橋・豊川", "愛知県その他"),
//        "三重" to listOf("三重県その他", "伊勢", "松阪", "桑名", "四日市", "津", "鈴鹿", "鳥羽・志摩"),
//        "北海道" to listOf("すすきの", "室蘭・登別・白老", "富良野・その他北海道", "札幌（札幌駅・大通）", "麻生・北24条（北区・東区）", "南郷・新札幌 白石・厚別・清田", "琴似・円山公園 中央・西・手稲", "平岸・澄川（豊平区・南区）", "旭川", "函館", "小樽・千歳・苫小牧・札幌近郊", "帯広・釧路・北見・河東郡"),
//        "青森" to listOf("青森市", "弘前", "八戸", "五所川原・青森西部", "十和田・三沢・おいらせ町", "むつ市・青森東部"),
//        "岩手" to listOf("宮古・岩手県その他", "盛岡", "花巻・北上・奥州・一関"),
//        "宮城" to listOf("仙台市", "宮城県その他", "青葉・宮城野・若林", "泉中央", "長町", "大崎市"),
//        "秋田" to listOf("秋田市", "仙北市・秋田県北部", "横手・秋田県南部"),
//        "山形" to listOf("山形市", "米沢", "酒田・鶴岡", "山形県その他"),
//        "福島" to listOf("郡山", "会津若松", "喜多方", "福島市", "いわき・福島県その他"),
//        "新潟" to listOf("新潟駅・万代", "新潟駅南", "古町周辺", "新潟東区・北区エリア", "新潟西エリア", "出来島・女池・桜木・鳥屋野潟周辺", "上越", "新発田", "佐渡・新潟県その他", "亀田・新津エリア", "長岡", "燕三条"),
//        "富山" to listOf("黒部・富山県その他", "富山市", "高岡"),
//        "石川" to listOf("金沢(片町･香林坊･にし茶屋周辺)", "金沢(金沢駅･近江町･ひがし茶屋)", "金沢市他・野々市・白山・内灘", "輪島・七尾・加賀・石川県その他"),
//        "福井" to listOf("小浜・福井県その他", "福井市", "敦賀", "坂井市"),
//        "山梨" to listOf("笛吹市", "富士吉田・河口湖", "北杜市", "山梨県その他", "甲府"),
//        "長野" to listOf("安曇野", "諏訪・茅野", "長野県その他", "長野市", "松本市", "軽井沢", "上田・佐久"),
//        "鳥取" to listOf("鳥取県その他", "鳥取市", "米子市"),
//        "島根" to listOf("島根県その他", "松江", "出雲市"),
//        "岡山" to listOf("岡山市", "倉敷（倉敷市中心部）", "倉敷(倉敷市郊外･児島･水島など)", "備前・岡山県その他"),
//        "広島" to listOf("広島市（広島市中心部）", "広島駅・横川・その他広島市内", "福山", "尾道", "呉", "広島県その他"),
//        "山口" to listOf("萩・山口県その他", "下関", "山口市", "宇部市", "周南市・下松市", "岩国", "防府"),
//        "徳島" to listOf("徳島市・徳島市周辺部", "徳島県その他"),
//        "香川" to listOf("高松市中心部", "高松市郊外", "丸亀･坂出･宇多津･善通寺･多度津", "坂出・香川県その他"),
//        "愛媛" to listOf("松山", "今治", "宇和島･東予･南予･愛媛県その他", "新居浜・西条"),
//        "高知" to listOf("高知市", "高知県その他"),
//        "福岡" to listOf("博多", "中洲・中洲川端", "天神・西中洲・春吉", "西新・姪浜・その他西エリア", "吉塚・香椎・その他東エリア", "大名・今泉・警固", "薬院･平尾･高砂", "大橋･その他南地区", "北九州（小倉・門司）", "北九州（八幡・黒崎・折尾）", "飯塚・筑紫野", "久留米", "福岡県その他"),
//        "佐賀" to listOf("佐賀市", "佐賀県その他", "唐津"),
//        "長崎" to listOf("長崎市", "佐世保・長崎県その他", "大村市・諫早市"),
//        "熊本" to listOf("熊本市(上通り･下通り･新市街)", "熊本市郊外", "八代", "阿蘇", "天草・熊本県その他"),
//        "大分" to listOf("大分市", "湯布院・由布市", "大分県その他", "別府", "中津市"),
//        "宮崎" to listOf("宮崎市中心部", "都城市", "宮崎県その他", "宮崎市郊外", "日南市", "延岡市・日向市"),
//        "鹿児島" to listOf("鹿児島市 天文館・中央駅・ふ頭", "鹿児島県その他", "騎射場・与次郎", "谷山・宇宿", "鹿児島市その他", "奄美・屋久島", "霧島市"),
//        "沖縄" to listOf("那覇", "糸満・豊見城・南風原・南城", "石垣島・宮古島・沖縄離島", "名護・恩納村・本部町", "読谷･北谷･宜野湾･浦添･嘉手納", "沖縄市・うるま・西原・北中城")
//        )
//
//    val smallAreaOptions: Map<String, List<String>> = mapOf(
//        "-" to listOf("-"),
//        "銀座・有楽町・新橋・築地・月島" to listOf("銀座", "新橋", "築地", "月島", "有楽町"),
//        "横浜" to listOf("横浜駅", "みなとみらい", "中華街", "元町", "桜木町"),
//        "大宮・さいたま新都心" to listOf("大宮駅", "さいたま新都心", "与野", "北与野", "浦和"),
//        "千葉・稲毛" to listOf("千葉駅", "稲毛", "幕張", "蘇我", "新浦安"),
//        "水戸" to listOf("水戸駅", "偕楽園", "笠間", "常陸太田", "ひたちなか"),
//        "宇都宮" to listOf("宇都宮駅", "日光", "鹿沼", "小山", "大田原"),
//        "前橋" to listOf("前橋駅", "高崎", "伊香保温泉", "草津温泉", "桐生"),
//        "大津" to listOf("大津駅", "比叡山", "草津", "彦根", "近江八幡"),
//        "烏丸御池・四条烏丸" to listOf("烏丸御池", "四条烏丸", "祇園", "嵐山", "伏見"),
//        "梅田" to listOf("梅田", "心斎橋", "難波", "天王寺", "京橋"),
//        "神戸" to listOf("三宮", "元町", "神戸港", "六甲山", "垂水"),
//        "奈良市" to listOf("奈良駅", "東大寺", "春日大社", "大和郡山", "橿原"),
//        "和歌山市" to listOf("和歌山市駅", "紀三井寺", "友ヶ島", "白浜", "高野山"),
//        "岐阜駅周辺・柳ヶ瀬・市役所" to listOf("岐阜駅", "柳ヶ瀬", "市役所", "長良川", "高山"),
//        "静岡駅周辺・葵区・駿河区" to listOf("静岡駅", "駿河区", "葵区", "浜松", "富士"),
//        "名古屋（名古屋駅/西区/中村区）" to listOf("名古屋駅", "栄", "金山", "千種", "今池"),
//        "三重県その他" to listOf("四日市", "伊勢", "桑名", "松阪", "津"),
//        "すすきの" to listOf("すすきの", "大通", "札幌駅", "琴似", "円山公園"),
//        "青森市" to listOf("青森駅", "弘前", "八戸", "むつ", "五所川原"),
//        "盛岡" to listOf("盛岡駅", "花巻", "北上", "一関", "二戸"),
//        "仙台市" to listOf("仙台駅", "青葉区", "泉中央", "長町", "塩釜"),
//        "秋田市" to listOf("秋田駅", "大曲", "横手", "能代", "由利本荘"),
//        "山形市" to listOf("山形駅", "蔵王温泉", "天童", "米沢", "新庄"),
//        "郡山" to listOf("郡山駅", "福島駅", "いわき", "会津若松", "喜多方"),
//        "新潟駅・万代" to listOf("新潟駅", "長岡", "上越", "佐渡", "燕三条"),
//        "富山市" to listOf("富山駅", "高岡", "黒部", "射水", "魚津"),
//        "金沢(片町･香林坊･にし茶屋周辺)" to listOf("片町", "香林坊", "にし茶屋", "金沢駅", "東茶屋街"),
//        "福井市" to listOf("福井駅", "敦賀", "坂井", "大野", "鯖江"),
//        "甲府" to listOf("甲府駅", "石和温泉", "富士吉田", "韮崎", "笛吹"),
//        "長野市" to listOf("長野駅", "軽井沢", "松本", "上田", "諏訪"),
//        "鳥取市" to listOf("鳥取駅", "米子", "倉吉", "境港", "三朝温泉"),
//        "松江" to listOf("松江駅", "出雲", "安来", "大田", "浜田"),
//        "岡山市" to listOf("岡山駅", "倉敷", "津山", "総社", "玉野"),
//        "広島市（広島市中心部）" to listOf("広島駅", "八丁堀", "紙屋町", "呉", "福山"),
//        "山口市" to listOf("山口駅", "下関", "宇部", "岩国", "萩"),
//        "徳島市・徳島市周辺部" to listOf("徳島駅", "鳴門", "小松島", "阿南", "吉野川"),
//        "高松市中心部" to listOf("高松駅", "丸亀", "観音寺", "坂出", "善通寺"),
//        "松山" to listOf("松山駅", "道後温泉", "今治", "宇和島", "西条"),
//        "高知市" to listOf("高知駅", "四万十", "土佐", "室戸", "須崎"),
//        "博多" to listOf("博多駅", "天神", "中洲", "薬院", "大名"),
//        "佐賀市" to listOf("佐賀駅", "唐津", "鳥栖", "武雄", "伊万里"),
//        "長崎市" to listOf("長崎駅", "佐世保", "島原", "諫早", "平戸"),
//        "熊本市(上通り･下通り･新市街)" to listOf("熊本駅", "上通り", "下通り", "新市街", "八代"),
//        "大分市" to listOf("大分駅", "別府", "中津", "佐伯", "臼杵"),
//        "宮崎市中心部" to listOf("宮崎駅", "都城", "日南", "延岡", "小林"),
//        "鹿児島市 天文館・中央駅・ふ頭" to listOf("天文館", "中央駅", "霧島", "奄美", "指宿"),
//        "那覇" to listOf("那覇駅", "首里", "おもろまち", "泊港", "新都心")
//    )
}