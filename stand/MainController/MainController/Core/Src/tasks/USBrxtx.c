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

//uint8_t subscribed[256];

extern void MX_USB_DEVICE_Init(void);
extern Settings_t settings;

void BadRquestReport();
//void SendLiveData();
void SendData(uint8_t startAddr, uint8_t endAddr);
void PutData(uint8_t startAddr, uint8_t endAddr, uint8_t* data);

void USBTask(void *argument)
{
  MX_USB_DEVICE_Init();
  for(;;)
  {
    osDelay(1000);
  }
}

void USB_CDC_RxHandler(uint8_t* Buf, uint32_t Len)
{
	if (Len >= 5){
		uint16_t startAddr = Buf[1] << 8 | Buf[2];
		uint16_t endAddr = Buf[3] << 8 | Buf[4];
		uint8_t* sa = (uint8_t*) &settings;
		switch (Buf[0]) {
		case COM_GET_PARAM:
			if (startAddr < endAddr && endAddr < sizeof(Settings_t))
			SendData(startAddr, endAddr);
			break;
		case COM_SET_PARAM:
			sa += startAddr;
			for (uint16_t i = 0; i < endAddr - startAddr; i++){
				*(__IO uint8_t*) (sa + i) = Buf[i];
			}
			SendData(startAddr, endAddr);
			break;
		case COM_SUBSCRIBE_PARAM:
			break;
		default:
			BadRquestReport();
			break;
		}

	} else
		BadRquestReport();
}

//void SendLiveData(){
//
//}

void SendData(uint8_t startAddr, uint8_t endAddr){
	CDC_Transmit_FS((uint8_t*) &settings + startAddr, endAddr - startAddr);
}

void BadRquestReport(){
	char Buf[40];
	sprintf(Buf, "{ \"request\": \"Bad request\" }");
	CDC_Transmit_FS((uint8_t*) &Buf, strlen(Buf));
}