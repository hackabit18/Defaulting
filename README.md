# med.ai

## Idea:
We have envisioned a prescription tracking android app which minimizes user input and removes the hassle from keeping track of which medicine is to be taken when. We allow the user to take image of the medications they have bought and our application will extract the medicine name. We have also built a robust database of almost all drugs which can be legally bought in India and reference that to furnish the user with all the relevant information associated with that medicine. 

We provide the dosage information for different age groups, based on the drug strength, known side effects and when the medicine is not to be taken. We allow the user to add when the medicine is to be taken based on the doctor's' prescription they have recieved. A major feature we aim at is to provide generic alternatives to significantly costlier brand name medicines. Finally, we remind the user to renew their prescription and provide affiliated sources to buy the medicines subject to availability of a valid doctor's prescription, thus having a commercial selling point.

We believe that the basic utility provided by the application of identifying and keeping track of the prescription will find widespread adoption as any non-trivial illness requires remembering upto 8-10 dosages per day with specific order and side effects of each of them. Our minimilistic design promises to remove the headache from managing the dosages and grow as the defacto solution for all the healthcare needs.

## Tools and Technologies Used:
#### OCR:
    1. Google Cloud Vision API
    2. Custom self made Java client for Google Vision API 

We use Google Cloud Vision API to perform OCR on the image captured from our android app. Experiments were made on Tesseract, another Image ML libray, and Vision API with Vision API performing significantly better and faster.

#### Server:
    1. Node.js / Express.js for server
    2. MongoDB for database storage of Medicine informatino.

#### Scraper:
    1. Python's scraping library BeautifulSoup for scraping medicine data.
