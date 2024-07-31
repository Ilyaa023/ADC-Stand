#include "USBrxtx.h"
#include "FreeRTOS.h"
#include "task.h"
#include "cmsis_os.h"
#include "usbd_cdc_if.h"
#include "settings.h"
#include <stdio.h>
#include <string.h>

#define COM_GET_PARAM  192
#define COM_SET_PARAM  193
#define COM_SUBSCRIBE_PARAM  194

#define STAND_TYPE "ADC"

const uint8_t STAND_REQUEST = 's'; 						//stand
const uint8_t DATA_TYPE[] = {'l'};						//live data
const uint8_t REQUEST_TYPE[] = {'g','s','l'}; // get set listen

uint8_t subscribed = 0;
	uint8_t buffer[600];

extern void MX_USB_DEVICE_Init(void);
extern Settings_t settings;

void BadRquestReport();
void checkEndpoints();
//void SendLiveData();
void SendData(uint8_t startAddr, uint8_t endAddr);
void PutData(uint8_t startAddr, uint8_t endAddr, uint8_t* data);

void USBTask(void * argument)
{
//  MX_USB_DEVICE_Init();
	for(uint8_t i = 0; i < 100; i++){
		buffer[i*6] = 'm';
		buffer[(i+1)*6] = i;
		buffer[(i+2)*6] = (i * 50) >> 8;
		buffer[(i+3)*6] = i * 50;
		buffer[(i+4)*6] = (i * 50 - 3000) >> 8;
		buffer[(i+5)*6] = i * 50 - 3000;
	}
  for(;;)
  {
    osDelay(1000);
//		if (subscribed == 1)
			CDC_Transmit_FS(buffer, 600);
  }
}

void USB_CDC_RxHandler(uint8_t* Buf, uint32_t Len)
{
	checkEndpoints(Buf, Len);
//	if (Len >= 5){
//		uint16_t startAddr = Buf[1] << 8 | Buf[2];
//		uint16_t endAddr = Buf[3] << 8 | Buf[4];
//		uint8_t* sa = (uint8_t*) &settings;
//		switch (Buf[0]) {
//		case COM_GET_PARAM:
//			if (startAddr < endAddr && endAddr < sizeof(Settings_t))
//			SendData(startAddr, endAddr);
//			break;
//		case COM_SET_PARAM:
//			sa += startAddr;
//			for (uint16_t i = 0; i < endAddr - startAddr; i++){
//				*(__IO uint8_t*) (sa + i) = Buf[i];
//			}
//			SendData(startAddr, endAddr);
//			break;
//		case COM_SUBSCRIBE_PARAM:
//			break;
//		default:
//			BadRquestReport();
//			break;
//		}
//
//	} else
//		BadRquestReport();
}

//void SendLiveData(){
//
//}

void SendData(uint8_t startAddr, uint8_t endAddr){
	CDC_Transmit_FS((uint8_t*) &settings + startAddr, endAddr - startAddr);
}

void getLivedata(){
	char buf[40];
	sprintf(buf, "Bad request");
	CDC_Transmit_FS((uint8_t*) &buf, strlen(buf));
}

void setLivedata(uint8_t* Buf){

	getLivedata();
}

void checkEndpoints(uint8_t* Buf, uint32_t Len){
	if (Buf[0] == STAND_REQUEST) {
		if (Len == 1) {
			CDC_Transmit_FS(STAND_TYPE, 3);
		} else if (Len > 2) {
			if (DATA_TYPE[0] == Buf[1]){
				if (Buf[2] == REQUEST_TYPE[0]) {
					getLivedata();
				} else if (Buf[2] == REQUEST_TYPE[1]) {
					if (Len > 3)
						setLivedata(Buf);
					else BadRquestReport();
				} else if (Buf[2] == REQUEST_TYPE[2]) {
					//getLivedata();
					if (subscribed == 0) subscribed = 1;
					else subscribed = 0;
				} else
					BadRquestReport();
			}
		}
	} else
		BadRquestReport();
}

void BadRquestReport(){
	char Buf[40];
	sprintf(Buf, "{ \"request\": \"Bad request\" }");
	CDC_Transmit_FS((uint8_t*) &Buf, strlen(Buf));
}