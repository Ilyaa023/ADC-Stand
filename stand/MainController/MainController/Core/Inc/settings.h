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
	float32_t output10v;
	float32_t output5av;
} VoltageModule_t;

typedef __packed struct {
	uint8_t standId;
	uint16_t USBUpdatePeriod;
} GeneralSettings_t;

typedef __packed struct {
	VoltageModule_t voltageModule;
	Signal_t signal;
	GeneralSettings_t generalSettings;
} Settings_t;

void InitSettings();