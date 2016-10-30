package edu.utdallas.codered.halloDoc.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParser {
	
	private static final String USER_AGENT = "Mozilla/5.0";
	 
	public static void mainer(String[] args) throws Exception {
		String disease = "cold hands";
		String symptomResponse = sendPost(20, "M", disease);
		
		System.out.println(symptomResponse);
		
		if (symptomResponse != null) {
//			JSONObject object = new JSONObject();
//			object.put("symptoms", new JSONArray(symptomResponse));
////			object.put("disease", value)
			
		}
		
	}
 
	private static String sendPost(int age, String gender, String symptom) throws Exception {
		StringBuilder answer = new StringBuilder();
		
		String url = "http://symptoms.webmd.com/scapp/SymptomCheckerAPI.svc/symptomsearch";
		String responseString = null;
		
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		List<Header> postHeaders = new ArrayList<>();
		
		try {
			postHeaders.add(new BasicHeader("Host", "symptoms.webmd.com"));
			postHeaders.add(new BasicHeader("Connection", "keep-alive"));
			postHeaders.add(new BasicHeader("Accept", "application/json, text/javascript, */*; q=0.01"));
			postHeaders.add(new BasicHeader("Origin", "http://symptoms.webmd.com"));
			postHeaders.add(new BasicHeader("X-Requested-With", "XMLHttpRequest"));
			postHeaders.add(new BasicHeader("User-Agent",
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36"));
			postHeaders.add(new BasicHeader("Content-Type", "application/json; charset=UTF-8"));
			postHeaders.add(new BasicHeader("Referer", "http://symptoms.webmd.com/default.htm"));
			postHeaders.add(new BasicHeader("Accept-Encoding", "gzip, deflate"));
			postHeaders.add(new BasicHeader("Accept-Language", "en-US,en;q=0.8,hi;q=0.6"));
			postHeaders.add(new BasicHeader("Cookie",
					"brand=mywebmd; VisitorId=a0630316-7df6-4505-9aee-bd7f9ff3d95c; refpath=; webmd_geoLoc=; ck_consent=true; AMCVS_16AD4362526701720A490D45%40AdobeOrg=1; s_vi=[CS]v1|2C0AAE0D0507DFBF-4000011140004714[CE]; ads_perm={%22segvar%22:%22segvarl_a118963xl_z00466xl_a219735xl_a139416xl_z02078xl_z9879xl_a112593xl_a32550xl_z01451xl_z030138xl_a32541xl_z030142xl_z01446xl_z00468xl_a216766xl_allxl_a212880xl_z030030xl_a178194xl_a30278xl_z02082xl_z30308xl_a30302xl_z30287xl_z01433xl_z02077xl_z01432xl_a118903xl_z9880xl_z00483xl_z02084xl_z30286xl_z030137xl_z030273xl_a32533xl_z00478xl_z02089xl_202101xl_a030119xl_z02669xl_a32553xl_z01450xl_z01445xl_z030144xl_134951%20xl_z01954xl_z02095xl_a140843xl_a17819xl_a194024xl_a202334xl_z030139xl_z02074xl_a189530xl_z01447xl_z01435xl_a134953%20xl_a219094x%22}; bm_last_load_status=BLOCKING; bm_monthly_unique=true; bm_daily_unique=true; gab=1; ads_sess={%22pim%22:{%22c%22:%22pimc_1x%22%2C%22t%22:%22pimt_1477794846328x%22}}; nls2={%22a%22:0}; bm_sample_frequency=1; s_cc=true; AMCV_16AD4362526701720A490D45%40AdobeOrg=-227196251%7CMCIDTS%7C17105%7CMCMID%7C91491107876759790192693111855857949989%7CMCAAMLH-1478399642%7C9%7CMCAAMB-1478399648%7CNRX38WO0n5BH8Th-nqAG_A%7CMCOPTOUT-1477802048s%7CNONE%7CMCAID%7C2C0AAE0D0507DFBF-4000011140004714; __gads=ID=87d043f460c00a38:T=1477794846:S=ALNI_MYPzS6AAG3XyjDxxT_SX7mHWxnjGA; DotomiUser=AQECr40vOsHQkwESlBmJAQENdgE|161029|y; ui={%22expmatch%22:1%2C%22vtime%22:24629914}; olen=1200; WBMD_SC=[rot13n]{%22crefba%22:{%22vq%22:%226%22%2C%22svefgAnzr%22:%22Zr%22%2C%22traqre%22:6%2C%22eryngvbafuvc%22:67%2C%22zBO%22:4%2C%22qBO%22:74%2C%22lBO%22:6441}%2C%22vfFnivatFlzcgbzf%22:snyfr%2C%22unirFubjaUryc%22:gehr%2C%22fryrpgrqObqlCneg%22:ahyy%2C%22flzcgbzf%22:[]}; s_sq=webmdp1global%3D%2526pid%253Dsymptoms.webmd.com%25252Fchoose-symptoms%2526pidt%253D1%2526oid%253DSubmit%2526oidt%253D3%2526ot%253DSUBMIT"));
			post.setHeaders(postHeaders.toArray(new Header[0]));

			StringEntity strEntity = new StringEntity("{\"request\":{\"user\":{\"age\":" + age
					+ ",\"gender\":\"" + gender + "\",\"zip\":\"\",\"vid\":\"a0630316-7df6-4505-9aee-bd7f9ff3d95c\"},\"locale\":\"us\",\"searchterm\":\""
					+ symptom + "\"}}");
			
			post.setEntity(strEntity);

			// Execute request
			HttpResponse response = client.execute(post);
			HttpEntity responseEntity = response.getEntity();

			if (responseEntity != null) {
				responseString = EntityUtils.toString(responseEntity);
				responseString = responseString.substring(responseString.indexOf("{"));
				
				JSONObject responseJson = new JSONObject(responseString);
				JSONArray symptomsArray = ((JSONArray) ((JSONObject) responseJson.get("data")).get("symptoms"));
				
				boolean found = false;
				
				for (int idx = 0; idx < symptomsArray.length(); idx ++) {
					JSONObject symptomObject = (JSONObject) symptomsArray.get(idx);
					
					JSONArray symptomQclassArray = (JSONArray) symptomObject.get("qclss");
					
					if (symptomQclassArray != null && symptomQclassArray.length() > 0) {
						
						for (int innerIdx = 0; innerIdx < symptomQclassArray.length(); innerIdx ++) {
							JSONObject jsonObject = (JSONObject) symptomQclassArray.get(innerIdx);
							if (jsonObject.get("nm").toString().equals("Worse")) {
								answer.append(jsonObject.get("q").toString() + " ");
								JSONArray qualsArray =  ((JSONArray) jsonObject.get("quals"));
								
								for (int i = 0; i < qualsArray.length(); i ++) {
									JSONObject innerJsonObject = (JSONObject) qualsArray.get(i);
									answer.append(innerJsonObject.get("vl").toString() + " ");
								}
								
								found = true;
								break;
							}
						}
						
					}
					
					if (found)
						break;
				}
				
			} else {
				System.out.println("No Response");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Other exception = " + e.toString());
		} 
		
		return answer.toString();
	}

}
