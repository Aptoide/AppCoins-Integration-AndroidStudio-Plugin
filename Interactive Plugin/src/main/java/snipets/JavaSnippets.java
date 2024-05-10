package snipets;

public class JavaSnippets implements Snippets {
    @Override
    public String buildGradleCodeAllprojects() {
        return "\nallprojects {\n" +
                "    repositories {\n" +
                "        google()\n" +
                "        mavenCentral()\n" +
                "    }\n" +
                "}\n";
    }

    @Override
    public String appCoinsBillingDependency() {
        return "\n\timplementation(\"io.catappult:android-appcoins-billing:0.7.2.0\")";
    }

    @Override
    public String androidManifestGooglePlayPermissions() {
        return "<uses-permission android:name=\"com.android.vending.BILLING\" />\n";
    }

    @Override
    public String androidManifestAppCoinsPermissions() {
        return "<uses-permission android:name=\"com.appcoins.BILLING\" />\n" +
                "<uses-permission android:name=\"android.permission.INTERNET\" />";
    }

    @Override
    public String androidManifestQueries() {
        return "<queries>\n" +
                "    <package android:name=\"com.appcoins.wallet\" />\n" +
                "</queries>";
    }

    @Override
    public String appCoinsBillingStateListener() {
        return  "\n   AppCoinsBillingStateListener appCoinsBillingStateListener = new AppCoinsBillingStateListener() {\n" +
                "       @Override public void onBillingSetupFinished(int responseCode) {\n" +
                "       if (responseCode != ResponseCode.OK.getValue()) {\n" +
                "           Log.d(TAG, \"Problem setting up in-app billing: \" + responseCode);\n" +
                "           return;\n" +
                "       }\n" +
                "       \n" +
                "       // Check for pending and/or owned purchases\n" +
                "       checkPurchases();\n" +
                "       // Query in-app sku details\n" +
                "       queryInapps();\n" +
                "       // Query subscriptions sku details\n" +
                "       querySubs();\n" +
                "       Log.d(TAG, \"Setup successful. Querying inventory.\");\n" +
                "   }\n" +
                "\n" +
                "   @Override public void onBillingServiceDisconnected() {\n" +
                "           Log.d(\"Message: \", \"Disconnected\");\n" +
                "       }\n" +
                "   };" +
                "\n" +
                "   private void checkPurchases() {\n" +
                "       PurchasesResult purchasesResult = cab.queryPurchases(SkuType.inapp.toString());\n" +
                "       List<Purchase> purchases = purchasesResult.getPurchases();\n" +
                "  \n" +
                "       // queryPurchases of subscriptions will always return active and to consume subscription\n" +
                "       PurchasesResult subsResult = cab.queryPurchases(SkuType.subs.toString());\n" +
                "       List<Purchase> subs = subsResult.getPurchases();\n" +
                "   }";
    }

    @Override
    public String appCoinsBillingClient() {
        return "private lateinit var cab: AppcoinsBillingClient\n" +
                "    private val purchasesUpdatedListener =\n" +
                "    PurchasesUpdatedListener { responseCode: Int, purchases: List<Purchase> -> {\n" +
                "    //Defined in step 4\n" +
                "    }}\n\n" +
                "    override fun onCreate(savedInstanceState: Bundle ?) {\n" +
                "        \n\n" +
                "        val base64EncodedPublicKey = MY_KEY // Key obtained in Catappult's console\n" +
                "        cab = CatapultBillingAppCoinsFactory.BuildAppcoinsBilling(\n" +
                "            this,\n" +
                "            base64EncodedPublicKey,\n" +
                "            purchasesUpdatedListener\n" +
                "        )\n" +
                "        cab.startConnection(appCoinsBillingStateListener)\n" +
                "        \n\n" +
                "    }";
    }

    @Override
    public String checkPurchases() {
        return "private void checkPurchases() {\n" +
                "    PurchasesResult purchasesResult = cab.queryPurchases(SkuType.inapp.toString());\n" +
                "    List<Purchase> purchases = purchasesResult.getPurchases();\n" +
                "  \n" +
                "    // queryPurchases of subscriptions will always return active and to consume subscription\n" +
                "    PurchaseResult subsResult = cab.queryPurchases(SkuType.subs.toString());\n" +
                "    List<Purchase> subs = subsResult.getPurchases();\n" +
                "}";
    }

    @Override
    public String onPurchasesUpdated() {
        return "\n\tPurchasesUpdatedListener purchasesUpdatedListener = (responseCode, purchases) -> {\n" +
                "       if (responseCode == ResponseCode.OK.getValue()) {\n" +
                "           for (Purchase purchase : purchases) {\n" +
                "               String token = purchase.getToken();\n" +
                "           \n" +
                "               // After validating and attributing consumePurchase may be called \n" +
                "               // to allow the user to purchase the item again and change the purchase's state.\n" +
                "               // Also consume subscriptions to make them active, there will be no issue in consuming more than once\n" +
                "               cab.consumeAsync(token, consumeResponseListener);\n" +
                "           }\n" +
                "       } else {\n" +
                "           new AlertDialog.Builder(this).setMessage(\n" +
                "               String.format(Locale.ENGLISH, \"response code: %d -> %s\", responseCode,\n" +
                "                   ResponseCode.values()[responseCode].name()))\n" +
                "               .setPositiveButton(android.R.string.ok, (dialog, which) -> dialog.dismiss())\n" +
                "               .create()\n" +
                "               .show();\n" +
                "       }\n" +
                "   };";
    }

    @Override
    public String cabDeclaration() {
        return "private lateinit var cab: AppcoinsBillingClient";
    }

    @Override
    public String onCreate() {
        return "private AppcoinsBillingClient cab;\n" +
                "  PurchasesUpdatedListener purchaseUpdatedListener = (responseCode, purchases) -> {\n" +
                "  // Defined in step 4\n" +
                "  };\n" +
                "  protected void onCreate(Bundle savedInstanceState) {\n" +
                "    String base64EncodedPublicKey = MY_KEY // Key obtained in Catappult's console\n" +
                "    cab = CatapultBillingAppCoinsFactory.BuildAppcoinsBilling(\n" +
                "      this,\n" +
                "      base64EncodedPublicKey,\n" +
                "      purchasesUpdatedListener\n" +
                "    );\n" +
                "    cab.startConnection(appCoinsBillingStateListener);\n" +
                "  }";
    }

    @Override
    public String startPurchase() {
        return "\n    private void startPurchase(String sku, String developerPayload) {\n" +
                "       Log.d(TAG, \"Launching purchase flow.\");\n" +
                "       // Your sku type, can also be SkuType.subs.toString()\n" +
                "       String skuType = SkuType.inapp.toString();\n" +
                "       BillingFlowParams billingFlowParams =\n" +
                "           new BillingFlowParams(\n" +
                "               Skus.YOUR_SKU_ID,\n" +
                "               skuType,\n" +
                "               \"orderId=\" +System.currentTimeMillis(),\n" +
                "               developerPayload,\n" +
                "               \"BDS\"\n" +
                "           );\n" +
                "       \n" +
                "       //Make sure that the billing service is ready\n" +
                "       if (!cab.isReady()) {\n" +
                "       cab.startConnection(appCoinsBillingStateListener);\n" +
                "       }\n" +
                "\n" +
                "       final Activity activity = this;\n" +
                "       Thread thread = new Thread(() -> {\n" +
                "           final int responseCode = cab.launchBillingFlow(activity, billingFlowParams);\n" +
                "           runOnUiThread(() -> {\n" +
                "               if (responseCode != ResponseCode.OK.getValue()) {\n" +
                "                   AlertDialog.Builder builder = new AlertDialog.Builder(this);\n" +
                "                   builder.setMessage(\"Error purchasing with response code : \" + responseCode);\n" +
                "                   builder.setNeutralButton(\"OK\", null);\n" +
                "                   Log.d(TAG, \"Error purchasing with response code : \" + responseCode);\n" +
                "                   builder.create().show();\n" +
                "               }\n" +
                "           });\n" +
                "       });\n" +
                "       thread.start();\n" +
                "   }";
    }

    @Override
    public String onActivityResultManual() {
        return "cab.onActivityResult(requestCode, resultCode, data);";
    }

    @Override
    public String noOnActivityResult() {
        return "@Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {\n" +
                "   super.onActivityResult(requestCode, resultCode, data);\n" +
                "   cab.onActivityResult(requestCode, resultCode, data);\n" +
                "}";
    }

    @Override
    public String consumePurchase1() {
        return "\n\tConsumeResponseListener consumeResponseListener = new ConsumeResponseListener() {\n" +
                "       @Override public void onConsumeResponse(int responseCode, String purchaseToken) {\n" +
                "           Log.d(TAG, \"Consumption finished. Purchase: \" + purchaseToken + \", result: \" + responseCode);\n" +
                "\n" +
                "               if (responseCode == ResponseCode.OK.getValue()) {\n" +
                "                   Log.d(TAG, \"Consumption successful. Provisioning.\");\n" +
                "                   //Your SKU logic goes here\n" +
                "               } else {\n" +
                "                   Log.d(TAG,\"Error while consuming token: \" + purchaseToken);\n" +
                "               }\n" +
                "            Log.d(TAG, \"End consumption flow.\");\n" +
                "       }\n" +
                "   };";
    }

    @Override
    public String consumePurchase2() {
        return "private void consumePurchases() {\n\t" +
                "   PurchasesResult queryPurchasesResponse = cab.queryPurchases(SkuType.inapp.toString());\n\t" +
                "   List<Purchase> queryPurchases = queryPurchasesResponse.purchases;\n\t" +
                "       for (Purchase purchase : queryPurchases) {\n\t" +
                "           cab.consumeAsync(purchase.token, consumeResponseListener);\n\t" +
                "        }\n\t" +
                "    // You can add code here to also process the subscriptions" +
                "\n\t" +
                "    }\n\t";
    }

    @Override
    public String skuDetailsResponseListener() {
        return "\n\tSkuDetailsResponseListener skuDetailsResponseListener = (responseCode, skuDetailsList) -> {\n\t" +
                "        Log.d(TAG, \"Received skus $responseCode $skuDetailsList\");\n\t" +
                "        for (SkuDetails sku : skuDetailsList) {\n\t" +
                "            Log.d(TAG, \"sku details: $sku\");\n\t" +
                "        }\n\t" +
                "    };";
    }

    @Override
    public String callSkuDetails() {
        return "private void queryInapps() {\n" +
                "    List<String> inapps = new ArrayList<String>();\n" +
                "    // Fill the inapps with the skus of items\n" +
                "\n" +
                "    SkuDetailsParams skuDetailsParams = new SkuDetailsParams();\n" +
                "    skuDetailsParams.setItemType(SkuType.inapp.toString());\n" +
                "    skuDetailsParams.setMoreItemSkus(inapps);\n" +
                "    cab.querySkuDetailsAsync(skuDetailsParams, skuDetailsResponseListener);\n" +
                "}\n" +
                "\n" +
                "private void querySubs() {\n" +
                "    List<String> subs = new ArrayList<String>();\n" +
                "    // Fill the subs with the skus of subscriptions\n" +
                "\n" +
                "    SkuDetailsParams skuDetailsParams = new SkuDetailsParams();\n" +
                "    skuDetailsParams.setItemType(SkuType.subs.toString());\n" +
                "    skuDetailsParams.setMoreItemSkus(subs);\n" +
                "    cab.querySkuDetailsAsync(skuDetailsParams, skuDetailsResponseListener);\n" +
                "}";
    }

    public String ospIntent() {
        return "\n  public static void launchOsp(Activity activity) {\n" +
                "       try {\n" +
                "           String domain = \"com.appcoins.trivialdrivesample\";\n" +
                "           String product = \"sword.001\";\n" +
                "           \n" +
                "           String ospUrl = generateOspUrl(domain, product);\n" +
                "           \n" +
                "           Intent intent = new Intent(Intent.ACTION_VIEW);\n" +
                "           intent.setData(Uri.parse(ospUrl));\n" +
                "           \n" +
                "           if (isAppCoinsWalletInstalled(activity)) {\n" +
                "               intent.setPackage(\"com.appcoins.wallet\");\n" +
                "           }\n" +
                "           activity.startActivityForResult(intent, 10003);\n" +
                "       } catch (Exception e) {\n" +
                "           e.printStackTrace();\n" +
                "       }\n" +
                "   }\n" +
                "\n" +
                "   private static String generateOspUrl(String domain, String product) {\n" +
                "     // TODO: Send a request to obtain the OSP URL from your server and then return it\n" +
                "     return \"https://apichain.catappult.io/transaction/inapp?domain=com.appcoins.trivialdrivesample&product=sword.001&callback_url=https%3A%2F%2Fmygamestudio.co%2Fappcoins%3Fout_trade_no%3D1234&signature=49bc6dac9780acfe5419eb16e862cf096994c15f807313b04f5a6ccd7717e78e\";\n" +
                "   }\n" +
                "\n" +
                "   private static boolean isAppCoinsWalletInstalled(Activity activity) {\n" +
                "       PackageManager packageManager = activity.getApplicationContext().getPackageManager();\n" +
                "       Intent intentForCheck = new Intent(Intent.ACTION_VIEW);\n" +
                "       if (intentForCheck.resolveActivity(packageManager) != null) {\n" +
                "           try {\n" +
                "               packageManager.getPackageInfo(\"com.appcoins.wallet\", PackageManager.GET_ACTIVITIES);\n" +
                "               return true;\n" +
                "           } catch (PackageManager.NameNotFoundException e) {}\n" +
                "       }\n" +
                "       \n" +
                "       return false;\n" +
                "   }";
    }

    public String ospQuery() {
        return "https://apichain.catappult.io/transaction/inapp?\\\n" +
                "product=sword.001&" +
                "omain=com.appcoins.trivialdrivesample&\\\n" +
                "callback_url=https%3A%2F%2Fwww.\\\n" +
                "mygamestudio.com%2FcompletePurchase%3FuserId%3D1234&\\\n" +
                "signature=91e3488303d93eb637e57f6abb7908837b9d8a3144\\\n" +
                "261aad4b2247de3b1c525a";
    }

    public String ospPHP(){
        return "$product = 'sword.001';\n" +
                "$domain = 'com.appcoins.trivialdrivesample';\n" +
                "$callback_url = 'https://www.mygamestudio.com/completePurchase?userId=1234';\n" +
                "$encoded_callback_url = urlencode($callback_url);\n" +
                "\n" +
                "$url = 'https://apichain.catappult.io/transaction/inapp';\n" +
                "$url .= '?product='.$product;\n" +
                "$url .= '&domain='.$domain;\n" +
                "$url .= '&callback_url='.$encoded_callback_url;\n" +
                "\n" +
                "$SECRET_KEY = 'secret';\n" +
                "$signature = hash_hmac('sha256', $url, $SECRET_KEY, false);\n" +
                "$signed_url = $url.'&signature='.$signature;";
    }

    public String ospJavaScript(){
        return "const crypto = require('crypto');\n" +
                "\n" +
                "let product = 'sword.001';\n" +
                "let domain = 'com.appcoins.trivialdrivesample';\n" +
                "let callback_url = 'https://www.mygamestudio.com/completePurchase?userId=1234';\n" +
                "let encoded_callback_url = encodeURIComponent(callback_url);\n" +
                "\n" +
                "let url = 'https://apichain.catappult.io/transaction/inapp';\n" +
                "url += '?product=' + product;\n" +
                "url += '&domain=' + domain;\n" +
                "url += '&callback_url=' + encoded_callback_url;\n" +
                "\n" +
                "let secret_key = 'secret';\n" +
                "let signature = crypto.createHmac(\"sha256\", secret_key).update(url).digest('hex');\n" +
                "let signed_url = url + '&signature=' + signature;";
    }

    public String ospPython(){
        return "import urllib.parse\n" +
                "import hmac\n" +
                "import hashlib\n" +
                "\n" +
                "product = \"sword.001\"\n" +
                "domain = \"com.appcoins.trivialdrivesample\"\n" +
                "callback_url = \"https://www.mygamestudio.com/completePurchase?userId=1234\"\n" +
                "encoded_callback_url = urllib.parse.quote(callback_url, safe=\"\")\n" +
                "\n" +
                "url = \"https://apichain.catappult.io/transaction/inapp\"\n" +
                "url += \"?product=\" + product\n" +
                "url += \"&domain=\" + domain\n" +
                "url += \"&callback_url=\" + encoded_callback_url\n" +
                "\n" +
                "secret_key = b'secret'\n" +
                "signature = hmac.new(secret_key, url.encode(\"utf-8\"), hashlib.sha256).hexdigest()\n" +
                "signed_url = url + \"&signature=\" + signature";
    }

    @Override
    public String serverCheckReceipt() {
        return  "{\n" +
                "  \"Store\": \"storeName\",\n" +
                "  \"TransactionID\": \"anIdWithNumbersAndLetters\",\n" +
                "  \"Payload\": {\n" +
                "    \"ItemType\": \"inapp\",\n" +
                "    \"ProductId\": \"full_trajectory\",\n" +
                "    \"GameOrderId\": \"anIdWithNumbersAndLetters\",\n" +
                "    \"OrderQueryToken\": \"aLargeIdWithNumbersAndLetters\",\n" +
                "    \"StorePurchaseJsonString\": {\n" +
                "      \"developerPayload\": \"unity://unity3d.comcpOrderId=anIdWithNumbersAndLetters&payload=\",\n" +
                "      \"itemType\": \"inapp\",\n" +
                "      \"orderId\": \"catappult.inapp.purchase.anIdWithNumbersAndLetters\",\n" +
                "      \"originalJson\": {\n" +
                "        \"orderId \": \"anIdWithNumbersAndLetters\",\n" +
                "        \"packageName\": \"your.package.name\",\n" +
                "        \"productId \": \"yourSKU\",\n" +
                "        \"purchaseTime\": 123456789,\n" +
                "        \"purchaseToken\": \"catappult.inapp.purchase.anIdWithNumbersAndLetters\",\n" +
                "        \"purchaseState\": 0,\n" +
                "        \"developerPayload\":\"unity://unity3d.com?cpOrderId=anIdWithNumbersAndLetters&payload=\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";
        }

    @Override
    public String serverCheckAPIRequest() {
        return "GET https://api.catappult.io/product/8.20191001/google/inapp/v3/applications/**packageName**/purchases/products/**productId**/tokens/**token**";
    }

    public String serverCheckAPIRequestSubscriptions() {
        return "GET https://api.catappult.io/product/8.20191001/google/inapp/v3/applications/**packageName**/purchases/subscriptions/**productId**/tokens/**token**";
    }

    @Override
    public String serverCheckRequestPython() {
        return "def validate_purchase(self, package_name: str, sku: str,\n" +
                "                      purchase_token: str, access_token: str) -> bool:\n" +
                " \n" +
                "    api_purchase_url = \"https://api.catappult.io/product/8.20191001/google/inapp/v3/\" \\\n" +
                "           \"applications/{packageName}/purchases/products/{productId}/tokens/\" \\\n" +
                "           \"{purchaseToken}\"\n" +
                " \n" +
                "    response = requests.get(api_purchase_url\n" +
                "                            .format(packageName=package_name, productId=sku,\n" +
                "                                    purchaseToken=purchase_token))\n" +
                " \n" +
                "    if response.status_code == 200:\n" +
                "        return True\n" +
                "    else:\n" +
                "        return False";
    }

    @Override
    public String serverCheckRequestJava() {
        return "private boolean validatePurchase(String packageName, String sku, \n" +
                "                                 String purchaseToken, String accessToken\n" +
                "                                ) throws Exception {\n" +
                "    String apiPurchaseUrl = String.format(\"https://api.catappult.io/product/8.20191001/\" +                                 \"inapp/google/v3/applications/%s/purchases/products/%s/tokens/%s\", \n" +
                "            packageName, sku, purchaseToken);\n" +
                "    HttpGet request = new HttpGet(apiPurchaseUrl);\n" +
                "\n" +
                "    Request request = new Request.Builder()\n" +
                "                .url(apiPurchaseUrl)\n" +
                "                .build();\n" +
                "    try (Response response = httpClient.newCall(request).execute()) {\n" +
                "        return response.isSuccessful();\n" +
                "    }\n" +
                "}";
    }

    @Override
    public String serverCheckRequestPHP() {
        return "function validatePurchase($packageName, $sku, \n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t$purchaseToken, $accessToken) {\n" +
                "    $curl = curl_init();\n" +
                "    $apiPurchaseUrl = \n" +
                "'https://api.catappult.io/product/8.20191001/inapp/google/v3/applications/' . $packageName . '/purchases/products/' . $sku . '/tokens/' . $purchaseToken\n" +
                "\n" +
                "    curl_setopt($ch, CURLOPT_URL, $apiPurchaseUrl);\n" +
                "    curl_setopt($ch, CURLOPT_HEADER, true);\n" +
                "    curl_setopt($ch, CURLOPT_NOBODY, true);\n" +
                "    $response = curl_exec($ch);\n" +
                "    $httpcode = curl_getinfo($ch, CURLINFO_HTTP_CODE);\n" +
                "    curl_close($ch);\n" +
                "    if ($httpcode == 200) {\n" +
                "        return true;\n" +
                "    } else {\n" +
                "        return false;\n" +
                "    }\n" +
                "}";
    }

    @Override
    public String serverCheckResponse() {
        return "{\"resource\":\n" +
                "\t{\n" +
                "    \"kind\": \"androidpublisher#productPurchase\",\n" +
                "    \"purchaseTimeMillis\": long,\n" +
                "    \"purchaseState\": integer,\n" +
                "    \"consumptionState\": integer,\n" +
                "    \"developerPayload\": string,\n" +
                "    \"orderId\": string,\n" +
                "    \"acknowledgementState\": integer,\n" +
                "    \"purchaseToken\": string,\n" +
                "    \"productId\": string,\n" +
                "    \"regionCode\": string\n" +
                "\t}\n" +
                "}";
    }
}
