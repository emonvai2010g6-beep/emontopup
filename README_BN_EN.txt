EMON TOP-UP — WEBSITE + ANDROID WEBVIEW STARTER

FOLDER: website
- index.html: responsive Bengali demo top-up website
- Current product prices are examples only. Update them before publishing.
- The order form is DEMO ONLY; it does not send or save orders and does not accept payment.

HOW TO PUBLISH THE WEBSITE WITH GITHUB PAGES
1. Sign in to GitHub and create a new public repository, e.g. emon-top-up.
2. Upload website/index.html to the repository root (or upload only the contents of website/).
3. Open repository Settings > Pages.
4. Under Build and deployment, choose Deploy from a branch, select main and /(root), then Save.
5. Wait for GitHub Pages to show the public HTTPS website URL.
6. Test the URL on your phone.

ANDROID WEBVIEW
- Open the separate Android starter ZIP/project in Android Studio.
- In MainActivity.java, replace WEBSITE_URL with your real GitHub Pages HTTPS URL.
- Sync Gradle, test on a phone, then Build > Build APK(s).

IMPORTANT
- This package is a starter template, not a finished APK.
- Before accepting real payments/orders, connect a secure backend and a legitimate payment provider/top-up supplier.
- Do not request customers' game account passwords.
- GitHub Pages serves static files; it does not securely process orders or payments by itself.
