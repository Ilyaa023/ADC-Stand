//#include "signal.h"
#include "settings.h"

//Signal_t signal = {0};
uint16_t counter = 0;
//uint8_t signalBuffer[512] = {0};

extern TIM_HandleTypeDef htim3;
extern TIM_HandleTypeDef htim4;
extern DMA_HandleTypeDef hdma_tim3_ch4_up;
extern Settings_t settings;

void CalculateMemoryBuffer(){

}

void CalculateBuffer(){
	switch (settings.signal.form){

	case TRIANGLE:
		for (uint16_t i = 0; i < 512; i++){
			uint16_t tmp = i % 256 * settings.signal.amplitude / 256 + settings.signal.offset;
			if (tmp > 255)
				tmp = 255;
			settings.signal.signalBuffer[i] = tmp;
		}
		break;

	case SQUARE:
		for (uint16_t i = 0; i < 512; i++)
			if (i>>7 & 1 )
				settings.signal.signalBuffer[i] = 0 + settings.signal.offset;
			else {
				uint16_t tmp;
				tmp = settings.signal.amplitude + settings.signal.offset;
				if (tmp > 255)
					tmp = 255;
				settings.signal.signalBuffer[i] = tmp;
			}
		break;

	case SINE:
		for (uint16_t i = 0; i < 512; i++){
			uint16_t tmp;
			if (i < 256)
				tmp = (256*i - i*i + 16384) * settings.signal.amplitude / 32768 + settings.signal.offset;
			else
				tmp = (i*i - 768*i + 147456) * settings.signal.amplitude / 32768 + settings.signal.offset;
			if (tmp > 256)
				tmp = 255;
			settings.signal.signalBuffer[i] = tmp;
		}
		break;

	case CONST:
		for (uint16_t i = 0; i < 512; i++)
			settings.signal.signalBuffer[i] = settings.signal.offset;

	default:
		CalculateMemoryBuffer();
		break;
	}
//	signal.PWMout = INITIAL_OUT;
	counter = 0;
}

void SignalInit(){
	settings.signal.amplitude = 128;
	settings.signal.offset = 75;
	settings.signal.frequency = 500;
	settings.signal.form = SINE;
        settings.signal.outConfig = OUT_ON;
       
	CalculateBuffer();
}

//uint8_t GetNext(){
//	if (counter > 511)
//		counter = 0;
//	return signal.PWMout = signalBuffer[counter++];
//}
//
//uint16_t* GetSignal(){
//	return (uint16_t*) &signal.PWMout;
//}

void SetForm(SignalForm_t form){
	settings.signal.form = form;
	CalculateBuffer();
}

void SetAmplitude(uint8_t ampl){
	settings.signal.amplitude = ampl;
	CalculateBuffer();
}
void SetOffset(uint8_t offset){
	settings.signal.offset = offset;
	CalculateBuffer();
}

void SetFrequency(uint16_t freq){
//	if (signal.form == CONST)
//		freq = 1;
//	signal.frequency = freq;
//	if (freq > 250){
//		signal._prescaler = 0;
//		signal._arr = 480000000 / 256 / freq;
//	}
//	else if (freq > 1250) {
//		signal._prescaler = 1;
//		signal._arr = 240000000 / 256 / freq;
//	}
//	else if (freq > 720) {
//		signal._prescaler = 2;
//		signal._arr = 120000000 / 256 / freq;
//	}
//	else if (freq > 360) {
//		signal._prescaler = 3;
//		signal._arr = 60000000 / 256 / freq;
//	}
//	else if (freq > 180) {
//		signal._prescaler = 4;
//		signal._arr = 30000000 / 256 / freq;
//	}
//	else if (freq > 100) {
//		signal._prescaler = 5;
//		signal._arr = 15000000 / 256 / freq;
//	}
//	else
//		signal._prescaler = 6;
//		signal._arr = 7500000 / 256 / freq;
//
//	if (signal.form == SINE || signal.form == USER)
//		freq /= 2;
}

void RunSignal(){
	HAL_DMA_Start(&hdma_tim3_ch4_up, (uint32_t) &settings.signal.signalBuffer[0], (uint32_t) &htim4.Instance->CCR2, 512);
	__HAL_TIM_ENABLE_DMA(&htim3, TIM_DMA_CC4);
	__HAL_TIM_ENABLE_DMA(&htim3, TIM_DMA_UPDATE);
	HAL_TIM_Base_Start(&htim3);
}

void StopSignal(){

}