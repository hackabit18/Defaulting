import requests
from bs4 import BeautifulSoup
import string
import json

'''
	script starts from here
'''
a_to_z = string.ascii_lowercase[:26]

all_ascii_string = string.printable

json_list = []
cnt = 0
with open('urls_names/a.txt', 'r') as file:
	medicines = file.read().split('\n')
	for medicine in medicines:
		if(cnt > 500):
				break
		med_name = medicine.split(";", 1)[0]
		url = medicine.split(";", 1)[1]		

		# scrape medicine page using url
		try:
			response_object = requests.get(url, timeout = 45)
			soup = BeautifulSoup(response_object.content, 'lxml')
			drug_header_list = soup.find_all("div", {"class": "drug-header"})
			drug_data_list = soup.find_all("p", {"class": "drug-content"})

			# create information using the fetched and parsed data	
			med_info = {}
			med_info["drug-name"] = med_name
			med_info["side-effects"] = ""
			med_info["dosage"] = ""
			med_info["when-ntb-taken"] = ""
			for header, data in zip(drug_header_list, drug_data_list):
				flag = False
				header_string = header.text
				data_string = data.text
				data_string = data_string.replace("â¢  ", "")    # double check
				data_string = data_string.replace("\r", "")	      # double check
				data_string = ''.join(i for i in data_string if i in all_ascii_string)
				if header_string.startswith("Side"):
					med_info['side-effects'] = data_string
				elif header_string.startswith("Dosage"):
					med_info['dosage'] = data_string
				elif header_string.startswith('When'):
					med_info['when-ntb-taken'] = data_string
			#print(med_info)
			json_list.append(med_info)
			cnt = cnt + 1
			print(cnt)
		except:
			print("error aaya")



json_list_string = ','.join(str(x) for x in json_list)

json_list_string = '[' + json_list_string + ']'

print(json.dumps(json_list_string))



#json_list_string.replace('\r', '')
print("string is: \n%s\n\n" % (json_list_string))
with open('a1.txt', 'a+') as output_file:
	output_file.write(json_list_string)

