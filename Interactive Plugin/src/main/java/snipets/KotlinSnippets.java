package snipets;

public class KotlinSnippets implements Snippets {
    @Override
    public String buildGradleCodeAllprojects() {
        return "allprojects {                               \n" +
                "    repositories {                         \n" +
                "        google()                           \n" +
                "        mavenCentral()                     \n" +
                "    }                                      \n" +
                "}                                          \n";
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
        return "val appCoinsBillingStateListener: AppCoinsBillingStateListener =\n" +
        "        object : AppCoinsBillingStateListener {\n" +
        "    override fun onBillingSetupFinished(responseCode: Int) {\n" +
        "        if (responseCode != ResponseCode.OK.value) {\n" +
        "            Log.d(TAG, \"Problem setting up in-app billing: $responseCode\")\n" +
        "            return\n" +
        "        }\n" +
        "        // Check for pending and/or owned purchases\n" +
        "        checkPurchases()\n" +
        "        // Query in-app sku details\n" +
        "        queryInapps()\n" +
        "        // Qury subscriptions sku details\n" +
        "        querySubs()\n" +
        "        Log.d(TAG, \"Setup successful. Querying inventory.\")\n" +
        "    }\n" +
        "    override fun onBillingServiceDisconnected() {\n" +
        "        Log.d(\"Message: \", \"Disconnected\")\n" +
        "    }\n" +
        "}";
    }

    @Override
    public String onPurchasesUpdated() {
        return "private var purchasesUpdatedListener = \n" +
            "PurchasesUpdatedListener { responseCode: Int, purchases: List<Purchase> ->\n" +
            "   if (responseCode == ResponseCode.OK.value) {\n" +
            "     for (purchase in purchases) {\n" +
            "        token = purchase.token\n" +
            "        // After validating and attributing consumePurchase may be called\n" +
            "        // to allow the user to purchase the item again.\n" +
            "        // You might also want to check the purchase type to prevent consumption of subscription\n" +
            "        // consumeResponseListener is explained in point 6\n" +
            "        cab.consumeAsync(token, consumeResponseListener);\n" +
            "      }\n" +
            "   } else {\n" +
            "       AlertDialog.Builder(this).setMessage(\n" +
            "                    String.format(\n" +
            "                            Locale.ENGLISH, \"response code: %d -> %s\", responseCode,\n" +
            "                            ResponseCode.values()[responseCode].name\n" +
            "                    )\n" +
            "            )\n" +
            "       .setPositiveButton(android.R.string.ok) { dialog, which -> dialog.dismiss() }\n" +
            "       .create()\n" +
            "       .show()\n" +
            "   }\n" +
            "}\n";
    }

    @Override
    public String cabDeclaration() {
        return "private AppcoinsBillingClient cab;";
    }

    @Override
    public String onCreate() {
        return "val base64EncodedPublicKey = MY_KEY\n" +
        "    cab = CatapultBillingAppCoinsFactory.BuildAppcoinsBilling(\n" +
        "            this,\n" +
        "            base64EncodedPublicKey,\n" +
        "            purchasesUpdatedListener\n" +
        "    )\n" +
        "    cab.startConnection(appCoinsBillingStateListener)\n";
    }

    @Override
    public String startPurchase() {
        return "private fun startPurchase(sku: String, developerPayload: String) {\n" +
                "   Log.d(TAG, \"Launching purchase flow.\");\n" +
                "   // Your sku type, can also be SkuType.subs.toString()\n" +
                "   val skuType = SkuType.inapp.toString()\n" +
                "   val billingFlowParams = BillingFlowParams(\n" +
                "        sku,\n" +
                "        skuType,\n" +
                "        \"orderId=\" + System.currentTimeMillis(),\n" +
                "        developerPayload,\n" +
                "        \"BDS\"\n" +
                "   )\n" +
                "   if (!cab.isReady) {\n" +
                "       cab.startConnection(appCoinsBillingStateListener)\n" +
                "   }\n" +
                "   val activity: Activity = this\n" +
                "   val thread = Thread {\n" +
                "       val responseCode = cab.launchBillingFlow(activity, billingFlowParams)\n" +
                "       runOnUiThread {\n" +
                "           if (responseCode != ResponseCode.OK.value) {\n" +
                "               val builder =\n" +
                "               AlertDialog.Builder(this)\n" +
                "               builder.setMessage(\"Error purchasing with response code : $responseCode\")\n" +
                "               builder.setNeutralButton(\"OK\", null)\n" +
                "               Log.d(TAG, \"Error purchasing with response code : $responseCode\")\n" +
                "                   builder.create().show()\n" +
                "           }\n" +
                "       }\n" +
                "   }\n" +
                "   thread.start()\n" +
                "}\n";
    }

    @Override
    public String onActivityResultManual() {
        return "cab.onActivityResult(requestCode, resultCode, data)\n";
    }

    @Override
    public String noOnActivityResult() {
        return "override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {\n" +
                "   super.onActivityResult(requestCode, resultCode, data);\n" +
                "   cab.onActivityResult(requestCode, resultCode, data);\n" +
                "}\n";
    }

    @Override
    public String consumePurchase1() {
        return "val consumeResponseListener = ConsumeResponseListener {responseCode, purchaseToken ->\n\t" +
                "   Log.d(TAG, \"Consumption finished. Purchase: $purchaseToken, result: $responseCode\")\n\t" +
                "   if (responseCode == ResponseCode.OK.value) {\n\t" +
                "       Log.d(TAG, \"Consumption successful. Provisioning.\");\n\t" +
                "       //Your SKU logic goes here\n\t" +
                "   } else {\n\t" +
                "   complain(\"Error while consuming token: $purchaseToken\");\n\t" +
                "   }\n\t" +
                "   Log.d(TAG, \"End consumption flow.\");\n\t"+
                "}\n";
    }

    @Override
    public String consumePurchase2() {
        return "\nprivate void consumePurchases() {\n\t" +
                "   PurchasesResult queryPurchasesResponse = cab.queryPurchases(SkuType.inapp.toString());\n\t" +
                "   List<Purchase> queryPurchases = queryPurchasesResponse.purchases;\n\t" +
                "       for (Purchase purchase : queryPurchases) {\n\t" +
                "           cab.consumeAsync(purchase.token, consumeResponseListener);\n\t" +
                "        }\n\t" +
                "    // You can add code here to also process the subscriptions" +
                "\n\t" +
                "    }\n";
    }

    @Override
    public String skuDetailsResponseListener() {
        return "\n\tval skuDetailsResponseListener = SkuDetailsResponseListener {responseCode, skuDetailsList ->\n\t" +
            "   Log.d(TAG, \"Received skus $responseCode $skuDetailsList\")\n\t" +
            "   for (sku in skuDetailsList) {\n\t" +
            "       Log.d(TAG, \"sku details: $sku\")\n\t" +
            "       // You can add these details to a list in order to update\n\t" +
            "       // UI or use it in any other way\n\t" +
            "   }\n\t" +
            "}\n";
    }

    @Override
    public String callSkuDetails() {
        return "\n\tprivate fun queryInapps() {\n\t" +
                "   cab.querySkuDetailsAsync(\n\t" +
                "       SkuDetailsParams().apply{\n\t" +
                "           itemType = SkuType.inapp.toString()\n\t" +
                "           moreItemSkus = mutableListOf<String>() // Fill with the skus of items\n\t" +
                "       },\n\t" +
                "       skuDetailsResponseListener\n\t" +
                "   )\n\t" +
                "}\n\t" +
                "private fun querySubs() {\n\t" +
                "   cab.querySkuDetailsAsync(\n\t" +
                "       SkuDetailsParams().apply{\n\t" +
                "           itemType = SkuType.subs.toString()\n\t" +
                "           moreItemSkus = mutableListOf<String>() // Fill with the skus of subscriptions\n\t" +
                "       },\n\t" +
                "       skuDetailsResponseListener\n\t" +
                "   )\n\t" +
                "}\n";
    }
    public String ospIntent() {
        return "\n  fun launchOsp(activity: Activity) {\n" +
                "       try {\n" +
                "           val domain = \"com.appcoins.trivialdrivesample\"\n" +
                "           val product = \"sword.001\"\n" +
                "           \n" +
                "           val ospUrl = generateOspUrl(domain, product) \n" +
                "           \n" +
                "           val intent = Intent(Intent.ACTION_VIEW)\n" +
                "           intent.data = Uri.parse(ospUrl)\n" +
                "           \n" +
                "           if (isAppCoinsWalletInstalled(activity)) {\n" +
                "               intent.setPackage(\"com.appcoins.wallet\")\n" +
                "           }\n" +
                "           activity.startActivityForResult(intent, 10003)\n" +
                "       } catch (e: Exception) {\n" +
                "           e.printStackTrace()\n" +
                "       }\n" +
                "   }\n" +
                "\n" +
                "   private fun generateOspUrl(domain: String, product: String): String {\n" +
                "       // TODO: Send a request to obtain the OSP URL from your server and then return it\n" +
                "       return \"https://apichain.catappult.io/transaction/inapp?domain=com.appcoins.trivialdrivesample&product=sword.001&callback_url=https%3A%2F%2Fmygamestudio.co%2Fappcoins%3Fout_trade_no%3D1234&signature=49bc6dac9780acfe5419eb16e862cf096994c15f807313b04f5a6ccd7717e78e\"\n" +
                "   }\n" +
                "\n" +
                "   private fun isAppCoinsWalletInstalled(activity: Activity): Boolean {\n" +
                "       val packageManager = activity.applicationContext.packageManager\n" +
                "       val intentForCheck = Intent(Intent.ACTION_VIEW)\n" +
                "       if (intentForCheck.resolveActivity(packageManager) != null) {\n" +
                "           try {\n" +
                "               packageManager.getPackageInfo(\"com.appcoins.wallet\", PackageManager.GET_ACTIVITIES)\n" +
                "               return true\n" +
                "           } catch (e: PackageManager.NameNotFoundException) {}\n" +
                "       }\n" +
                "       \n" +
                "    return false\n" +
                "}";
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
