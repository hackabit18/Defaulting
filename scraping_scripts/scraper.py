import requests
from bs4 import BeautifulSoup
import string
import json
import os

def get_last_page_number(soup):
	last_page_anchor = soup.find("a", {"title": "Last Page"})
	last_page_url = last_page_anchor.get('href')
	last_page_number_string = last_page_url.split("&page=", 1)[1]
	last_page_number = int(last_page_number_string)
	return last_page_number

def get_medicines_info(soup_of_page):
	table_garbage = soup_of_page.find('table', class_ = "table-bordered table")
	#print(type(table_garbage)) #bs4.element.Tag

	anchors = table_garbage.find_all('a')
	#print(type(anchors)) #bs4.element.ResultSet
	medicines_info = {}
	for anchor in anchors:
		key = anchor.get('href')    #urls.append(anchor.get('href'))
		medicines_info[key] = anchor.text
	return medicines_info

def clean_med_name(med_name):
	med_name = med_name.split("(", 1)[0]
	return med_name.strip()

def get_unique_medicines_info(medicines_info):
	# initialize an empty set
	unique_med_names = set()
	unique_medicines_info = {}
	for url in medicines_info:
		med_name = medicines_info[url]
		med_name = clean_med_name(med_name)
		size_before_insertion = len(unique_med_names)
		unique_med_names.add(med_name)
		if size_before_insertion < len(unique_med_names):
			unique_medicines_info[med_name] = url
	return unique_medicines_info


''' 
	Script runs from here 
'''
a_to_z = string.ascii_lowercase[:26]
# a_to_z = 'z'
print("a_to_z %s" % a_to_z)

for character in a_to_z:
	print("currently scraping: %s" %character)
	request_url_for_first_page = 'https://www.medindia.net/drug-price/brand-index.asp?alpha=' + character
	written_to_current_file_atleast_once = False # character.txt  (character is a variable)
	try: 
		response_object_for_first_page = requests.get(request_url_for_first_page, timeout = 60)
		soup_of_first_page = BeautifulSoup(response_object_for_first_page.content, 'lxml')
		last_page_number = get_last_page_number(soup_of_first_page)
		page_number = 1

		

		# iterate on each page of a character 
		while page_number <= last_page_number:
			print("page no: %s" %page_number)
			#url = 'https://www.medindia.net/drug-price/brand-index.asp?alpha=a&page=' + str(page_number)
			request_url_for_current_page = request_url_for_first_page + '&page=' + str(page_number)
			page_number = page_number + 1

			

			try:
				response_object_for_current_page = requests.get(request_url_for_current_page, timeout = 60)
				soup_of_current_page = BeautifulSoup(response_object_for_current_page.content, 'lxml')

				medicines_info = get_medicines_info(soup_of_current_page)
				unique_medicines_info = get_unique_medicines_info(medicines_info)
				
				filename = str(character) + '.txt'
				just_opened_array_at_start = False
				# if written_to_current_file_atleast_once == False:
				# 	written_to_current_file_atleast_once = True
				# 	just_opened_array_at_start = True
				# open(filename, 'a+').write(json.dumps(unique_medicines`_info))
				with open(filename, 'a+') as file:
					for k, b in unique_medicines_info.items():
						file.write(k + ";" + b + "\n")
						# if (page_number != last_page_number and k != unique_medicines_info.keys()[-1]):
						# 	file.write("\n")
								
			except:
				print("Error is there")
				# pass
		# with open(str(character) + ".txt", 'a+') as file:
		# 	# file.seek(-1, os.SEEK_CURR)
		# 	file.write(']')
		# print("done: %s" %character)
	except:
		print("Error is there")
		# pass
