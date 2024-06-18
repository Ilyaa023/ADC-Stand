#include "stm32f1xx_hal.h"

typedef enum
{
	CONST,
  SINE,
	TRIANGLE,
	SQUARE,
	USER
} SignalForm_t;

typedef enum { OUT_ON, OUT_OFF } OutConfig_t;

typedef __packed struct {
	OutConfig_t outConfig;
	uint16_t amplitude;
	uint16_t offset;
	SignalForm_t form;
	uint16_t frequency;

//	uint16_t PWMout;

	uint16_t signalBuffer[512];
	uint16_t measuredVoltage;

//	uint16_t _prescaler;
//	uint16_t _arr;
} Signal_t;

void SignalInit();
uint8_t GetNext();
uint16_t* GetSignal();
void SetForm(SignalForm_t form);
void SetFrequency(uint16_t freq);
void SetAmplitude(uint8_t ampl);
void SetOffset(uint8_t offset);
void RunSignal();
void StopSignal();