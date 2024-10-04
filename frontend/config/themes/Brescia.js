import { definePreset } from '@primevue/themes';
import Nora from '@primevue/themes/nora';

const Brescia = definePreset(Nora, {
  semantic: {
    transitionDuration: '0s',
    primary: {
      50: '{emerald.50}',
      100: '{emerald.100}',
      200: '{emerald.200}',
      300: '{emerald.300}',
      400: '{emerald.400}',
      500: '{emerald.500}',
      600: '{emerald.600}',
      700: '{emerald.700}',
      800: '{emerald.800}',
      900: '{emerald.900}',
      950: '{emerald.950}',
    },
    colorScheme: {
      light: {
        surface: {
          0: '#ffffff',
          50: '{zinc.50}',
          100: '{zinc.100}',
          200: '{zinc.200}',
          300: '{zinc.300}',
          400: '{zinc.400}',
          500: '{zinc.500}',
          600: '{zinc.600}',
          700: '{zinc.700}',
          800: '{zinc.800}',
          900: '{zinc.900}',
        },
      },
      dark: {
        content: {
          background: '{zinc.950}',
        },
        surface: {
          0: '#ffffff',
          50: '{zinc.50}',
          100: '{zinc.100}',
          200: '{zinc.200}',
          300: '{zinc.300}',
          400: '{zinc.400}',
          500: '{zinc.500}',
          600: '{zinc.600}',
          700: '{zinc.700}',
          800: '{zinc.800}',
          900: '{zinc.900}',
        },
      },
    },
  },
  components: {
    button: {
      root: {
        label: {
          fontWeight: '900',
        },
      },
      colorScheme: {
        light: {
          root: {
            yellow: {
              background: '{yellow.100}',
              hoverBackground: '{yellow.200}',
              activeBackground: '{yellow.300}',
              borderColor: '{yellow.100}',
              hoverBorderColor: '{yellow.200}',
              activeBorderColor: '{yellow.300}',
              color: '{yellow.600}',
              hoverColor: '{yellow.700}',
              activeColor: '{yellow.800}',
              focusRing: {
                color: '{yellow.600}',
                shadow: 'none',
              },
            },
          },
          outlined: {
            secondary: {
              color: '{surface.900}',
            },
          },
          text: {
            secondary: {
              hoverBackground: '{surface.100}',
              activeBackground: '{surface.200}',
              color: '{surface.900}',
            },
          },
        },
        dark: {
          yellow: {
            background: '{yellow.100}',
            hoverBackground: '{yellow.200}',
            activeBackground: '{yellow.300}',
            borderColor: '{yellow.100}',
            hoverBorderColor: '{yellow.200}',
            activeBorderColor: '{yellow.300}',
            color: '{yellow.600}',
            hoverColor: '{yellow.700}',
            activeColor: '{yellow.800}',
            focusRing: {
              color: '{yellow.600}',
              shadow: 'none',
            },
          },
          outlined: {
            secondary: {
              color: '{surface.100}',
            },
          },
          text: {
            secondary: {
              color: '{surface.100}',
            },
          },
        },
      },
    },
    // accordion: {
    //   header: {
    //     padding: '1rem',
    //     fontWeight: '600',
    //     borderRadius: '0',
    //     borderWidth: '0',
    //     borderColor: '{content.border.color}',
    //     background: '{content.background}',
    //     toggleIcon: {
    //       color: '{text.muted.color}',
    //     },
    //   },
    // },
  },
});

export default {
  preset: Brescia,
  options: {
    darkModeSelector: '.dark',
  },
};
