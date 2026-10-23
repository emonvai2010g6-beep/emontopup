Emon Top-Up Android WebView Starter

এটি একটি Android Studio প্রজেক্টের স্টার্টার ফাইল। APK আগে থেকে তৈরি করা নেই।

শুরু করার ধাপ:
1. Android Studio-তে এই ফোল্ডারটি Open করুন।
2. app/src/main/java/com/emontopup/app/MainActivity.java খুলুন।
3. WEBSITE_URL = "https://YOUR-WEBSITE-DOMAIN.example" লাইনে আপনার নিজের লাইভ ওয়েবসাইটের HTTPS URL বসান।
   উদাহরণ: https://example.com
4. Android Studio-তে Gradle Sync করুন।
5. Run দিয়ে ফোনে পরীক্ষা করুন।
6. APK বানাতে Build > Build APK(s) ব্যবহার করুন।

গুরুত্বপূর্ণ:
- ওয়েবসাইটটি আগে অনলাইনে থাকতে হবে এবং HTTPS ব্যবহার করতে হবে।
- এই অ্যাপ ওয়েবসাইটের UI দেখায়; লগইন, অর্ডার, পেমেন্ট বা টপ-আপ কার্যকারিতা ওয়েবসাইট/সার্ভারেই চালু থাকতে হবে।
- পেমেন্ট ও লগইন ফ্লো, ফাইল আপলোড, external payment links এবং popup আগে বাস্তব ফোনে পরীক্ষা করুন।
- অচেনা সাইটে JavaScript bridge যোগ করবেন না।
- Google Play-তে প্রকাশের আগে বর্তমান Play policy ও target SDK requirements যাচাই করুন।
