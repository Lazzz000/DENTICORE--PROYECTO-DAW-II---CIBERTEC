/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,ts}"],
  theme: {
    extend: {
      colors: {
        primary: { DEFAULT: '#0F766E', hover: '#115E59' },
        sidebar: '#334155',
        surface: { main: '#F8FAFC', card: '#FFFFFF', border: '#E2E8F0', soft: '#ECFEFF' },
        ink: { DEFAULT: '#0F172A', muted: '#64748B' },
        status: { success: '#16A34A', warning: '#F59E0B', error: '#DC2626' }
      },
      fontFamily: { sans: ['Poppins', 'sans-serif'] },
      boxShadow: { 'card': '0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px -1px rgba(0, 0, 0, 0.1)' }
    },
  },
  plugins: [],
}
