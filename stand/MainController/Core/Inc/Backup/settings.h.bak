#include "stm32f1xx_hal.h"
#include <arm_math.h>
#include <math.h>
#include "signal.h"

#define STAND_ID		10
#define USB_PERIOD	100
#define AMPLITUDE		1024
#define OFFSET			0

typedef __packed struct {
	float32_t inputCurrent;
	float32_t maxInputCurrent;
	uint8_t overcurrrent;
	float32_t measuredOutAnalog;
	float32_t settedOutAnalog;
} VoltageModule_t;

typedef __packed struct {
	uint8_t standId;
	uint16_t USBUpdatePeriod;
	uint8_t subscribed;
} GeneralSettings_t;

typedef __packed struct {
	uint8_t nothing;
} ADCModule_t;

typedef __packed struct {
	VoltageModule_t voltageModule;
	Signal_t signal;
	GeneralSettings_t generalSettings;
	ADCModule_t ADCModule;
} Settings_t;

void InitSettings();