package snipets;

public class JavaSnippets implements Snippets {
    @Override
    public String buildGradleCodeAllprojects() {
        return "allprojects {\n" +
                "    repositories {\n" +
                "        google()\n" +
                "        mavenCentral()\n" +
                "    }\n" +
                "}\n";
    }

    @Override
    public String appCoinsBillingDependency() {
        return "\n\timplementation 'io.catappult:android-appcoins-billing:0.6.7.0'";
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
        return "AppCoinsBillingStateListener appCoinsBillingStateListener = new AppCoinsBillingStateListener() {\n" +
                "    @Override public void onBillingSetupFinished(int responseCode) {\n" +
                "      if (responseCode != ResponseCode.OK.getValue()) {\n" +
                "        complain(\"Problem setting up in-app billing: \" + responseCode);\n" +
                "        return;\n" +
                "      }\n" +
                "      callSkuDetails();\n" +
                "      updateUi();\n" +
                "\n" +
                "      Log.d(TAG, \"Setup successful. Querying inventory.\");\n" +
                "    }\n" +
                "\n" +
                "    @Override public void onBillingServiceDisconnected() {\n" +
                "      Log.d(\"Message: \", \"Disconnected\");\n" +
                "    }\n" +
                "  };";
    }

    @Override
    public String onPurchasesUpdated() {
        return "PurchasesUpdatedListener purchaseFinishedListener = (responseCode, purchases) -> {\n" +
                "      if (responseCode == ResponseCode.OK.getValue()) {\n" +
                "        for (Purchase purchase : purchases) {\n" +
                "          // These are examples of parameters you can use for purchase validation\n\t" +
                "          token = purchase.getToken();\n\t\t" +
                "          String purchasePayload = purchase.developerPayload;\n\t\t" +
                "          String signature = purchase.signature;\n\t\t" +
                "          String json = purchase.originalJson;\n\t\t" +
                "          consumePurchases();\n\t\t"+
                "        }\n" +
                "      } else {\n" +
                "        new AlertDialog.Builder(this).setMessage(\n" +
                "            String.format(Locale.ENGLISH, \"response code: %d -> %s\", responseCode,\n" +
                "                ResponseCode.values()[responseCode].name()))\n" +
                "            .setPositiveButton(android.R.string.ok, (dialog, which) -> dialog.dismiss())\n" +
                "            .create()\n" +
                "            .show();\n" +
                "      }\n" +
                "    };";
    }

    @Override
    public String cabDeclaration() {
        return "private lateinit var cab: AppcoinsBillingClient";
    }

    @Override
    public String onCreate() {
        return "cab = CatappultBillingAppCoinsFactory.BuildAppcoinsBilling(\n" +
                "      this,\n" +
                "      base64EncodedPublicKey,\n" +
                "      purchasesUpdatedListener\n" +
                "    );\n" +
                "    cab.startConnection(appCoinsBillingStateListener);";
    }

    @Override
    public String startPurchase() {
        return "private void startPurchase(String sku, String developerPayload) {\n" +
                "    Log.d(TAG, \"Launching purchase flow.\");\n" +
                "    // Your sku type, can also be SkuType.subs.toString()\n" +
                "    String skuType = SkuType.inapp.toString();\n" +
                "    BillingFlowParams billingFlowParams =\n" +
                "        new BillingFlowParams(\n" +
                "            Skus.YOUR_SKU_ID,\n" +
                "            skuType,\n" +
                "            \"orderId=\" +System.currentTimeMillis(),\n" +
                "            developerPayload,\n" +
                "            \"BDS\"\n" +
                "        );\n" +
                "    \n" +
                "    //Make sure that the billing service is ready\n" +
                "    if (!cab.isReady()) {\n" +
                "      startConnection();\n" +
                "    }\n" +
                "\n" +
                "    final Activity activity = this;\n" +
                "    Thread thread = new Thread(() -> {\n" +
                "      final int responseCode = cab.launchBillingFlow(activity, billingFlowParams);\n" +
                "      runOnUiThread(() -> {\n" +
                "        if (responseCode != ResponseCode.OK.getValue()) {\n" +
                "          AlertDialog.Builder builder = new AlertDialog.Builder(this);\n" +
                "          builder.setMessage(\"Error purchasing with response code : \" + responseCode);\n" +
                "          builder.setNeutralButton(\"OK\", null);\n" +
                "          Log.d(TAG, \"Error purchasing with response code : \" + responseCode);\n" +
                "          builder.create().show();\n" +
                "        }\n" +
                "      });\n" +
                "    });\n" +
                "    thread.start();\n" +
                "}\n";
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
        return "\n\tConsumeResponseListener consumeResponseListener = new ConsumeResponseListener() {\n\t" +
                "    @Override public void onConsumeResponse(int responseCode, String purchaseToken) {\n\t" +
                "      Log.d(TAG, \"Consumption finished. Purchase: \" + purchaseToken + \", result: \" + responseCode);\n" +
                "\n\t" +
                "      if (responseCode == ResponseCode.OK.getValue()) {\n" +
                "\n\t" +
                "        Log.d(TAG, \"Consumption successful. Provisioning.\");\n" +
                "\n\t" +
                "                //Your SKU logic goes here\n\t" +
                "      } else {\n\t" +
                "        complain(\"Error while consuming token: \" + purchaseToken);\n\t\t" +
                "      }\n\t" +
                "      Log.d(TAG, \"End consumption flow.\");\n\t" +
                "    }\n\t" +
                "  };\n";
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
        return "\tSkuDetailsResponseListener skuDetailsResponseListener = (responseCode, skuDetailsList) -> {\n\t" +
                "        Log.d(TAG, \"Received skus $responseCode $skuDetailsList\");\n\t" +
                "        for (SkuDetails sku : skuDetailsList) {\n\t" +
                "            Log.d(TAG, \"sku details: $sku\");\n\t" +
                "        }\n\t" +
                "    };";
    }

    @Override
    public String callSkuDetails() {
        return "\tprivate void queryInapps() {\n\t" +
                "        AList<String> inapps = ArrayList<String>()\n\t" +
                "        // Fill the inapps with the skus of items" +
                "\n\t" +
                "        SkuDetailsParams skuDetailsParams = SkuDetailsParams();\n\t" +
                "        skuDetailsParams.setItemType(SkuType.inapp.toString());\n\t" +
                "        skuDetailsParams.setMoreItemSkus(inapps);\n\t" +
                "        cab.querySkuDetailsAsync(skuDetailsParams, skuDetailsResponseListener);" +
                "\n\t" +
                "\tprivate void querySubs() { \n\t" +
                "        SkuDetailsParams skuDetailsParamsSubs = new SkuDetailsParams();\n\t" +
                "        skuDetailsParamsSubs.itemType = SkuType.subs.toString();\n\t" +
                "        skuDetailsParamsSubs.moreItemSkus(subs);\n\t" +
                "        cab.querySkuDetailsAsync(skuDetailsParamsSubs, skuDetailsResponseListener);\n\t" +
                "    }\n";
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
}
