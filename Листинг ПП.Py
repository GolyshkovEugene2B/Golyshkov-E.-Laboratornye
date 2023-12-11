#подключение библиотек и модулей
from PyQt5.QtGui import *
from PyQt5.QtWidgets import *
from PyQt5.QtCore import *
import random
import time
import ctypes

#добавление изображений
IMG_BOMB = QImage("./images/bomb.png")
IMG_FLAG = QImage("./images/heart.png")
IMG_START = QImage("./images/flag.png")
IMG_CLOCK = QImage("./images/clock.png")

#установка цветов
NUM_COLORS = {
    1: QColor('#f52732'),
    2: QColor('#9132B7'),
    3: QColor('#5f89b1'),
    4: QColor('#10A2F7'),
    5: QColor('#11bcd7'),
    6: QColor('#50c954'),
    7: QColor('#ec3170'),
    8: QColor('#e5951e')}

#сложность 
LEVELS = [(8, 10),(16, 40),(24, 99)]

#статусы игры
STATUS_READY = 0
STATUS_PLAYING = 1
STATUS_FAILED = 2
STATUS_SUCCESS = 3
STATUS_ICONS = {STATUS_READY: "./images/plus.png", STATUS_PLAYING: "./images/ok.png", STATUS_FAILED: "./images/x.png",STATUS_SUCCESS: "./images/o.png",}

#класс представляет плитку и информацию на карте
class Pos(QWidget):
    expandable = pyqtSignal(int, int)
    clicked = pyqtSignal()
    ohno = pyqtSignal()

#принимает позицию y, x
    def __init__(self, x, y, *args, **kwargs):
        super(Pos, self).__init__(*args, **kwargs)

        self.setFixedSize(QSize(20, 20))

        self.x = x
        self.y = y

    def reset(self):
        self.is_start = False
        self.is_mine = False
        self.adjacent_n = 0

        self.is_revealed = False
        self.is_flagged = False

        self.update()

    def paintEvent(self, event):
        p = QPainter(self)
        r.setRenderHint(QPainter.Antialiasing)

        r = event.rect()

        if self.is_revealed:
            color = self.palette().color(QPalette.Background)
            outer, inner = color, color
        else:
            outer, inner = Qt.gray, Qt.lightGray

        r.fillRect(r, QBrush(inner))
        pen = QPen(outer)
        pen.setWidth(1)
        r.setPen(pen)
        r.drawRect(r)

        if self.is_revealed:
            if self.is_start:
                r.drawPixmap(r, QPixmap(IMG_START))

            elif self.is_mine:
                r.drawPixmap(r, QPixmap(IMG_BOMB))

            elif self.adjacent_n > 0:
                pen = QPen(NUM_COLORS[self.adjacent_n])
                r.setPen(pen)
                f = r.font()
                f.setBold(True)
                r.setFont(f)
                r.drawText(r, Qt.AlignHCenter | Qt.AlignVCenter, str(self.adjacent_n))

        elif self.is_flagged:
            r.drawPixmap(r, QPixmap(IMG_FLAG))

    #запоминание мины игроком
    def flag(self):
        self.is_flagged = True
        self.update()

        self.clicked.emit()

    def reveal(self):
        self.is_revealed = True
        self.update()

    def click(self):
        if not self.is_revealed:
            self.reveal()
            if self.adjacent_n == 0:
                self.expandable.emit(self.x, self.y)

        self.clicked.emit()

    #назначение кнопок мыши
    def mouseReleaseEvent(self, e):

        if (e.button() == Qt.RightButton and not self.is_revealed):
            self.flag()

        elif (e.button() == Qt.LeftButton):
            self.click()

            if self.is_mine:
                self.ohno.emit()

#Класс представляет собой информацию о главном окне программы, кнопках и другой информации
class MainWindow(QMainWindow):
    def __init__(self, *args, **kwargs):
        super(MainWindow, self).__init__(*args, **kwargs)
        #Изменение названия окна
        self.setWindowTitle("Minesweeper")
        #Запрет разворачивания игры на весь экран
        self.setWindowFlags(Qt.CustomizeWindowHint | Qt.WindowMinimizeButtonHint | Qt.WindowCloseButtonHint)
        
        self.b_size, self.n_mines = LEVELS[1]

        w = QWidget()
        hb = QHBoxLayout()

        self.mines = QLabel()
        self.mines.setAlignment(Qt.AlignHCenter | Qt.AlignVCenter)

        self.clock = QLabel()
        self.clock.setAlignment(Qt.AlignHCenter | Qt.AlignVCenter)

        f = self.mines.font()
        f.setPointSize(24)
        f.setWeight(75)
        self.mines.setFont(f)
        self.clock.setFont(f)

        self._timer = QTimer()
        self._timer.timeout.connect(self.update_timer)
        self._timer.start(1000)  #таймер 

        self.mines.setText("%03d" % self.n_mines)
        self.clock.setText("000")

        self.button = QPushButton()
        self.button.setFixedSize(QSize(32, 32))
        self.button.setIconSize(QSize(32, 32))
        self.button.setIcon(QIcon("./images/o.png"))
        self.button.setFlat(True)

        self.button.pressed.connect(self.button_pressed)

        l = QLabel()
        l.setPixmap(QPixmar.fromImage(IMG_BOMB))
        l.setAlignment(Qt.AlignRight | Qt.AlignVCenter)
        hb.addWidget(l)

        hb.addWidget(self.mines)
        hb.addWidget(self.button)
        hb.addWidget(self.clock)

        l = QLabel()
        l.setPixmap(QPixmar.fromImage(IMG_CLOCK))
        l.setAlignment(Qt.AlignLeft | Qt.AlignVCenter)
        hb.addWidget(l)

        vb = QVBoxLayout()
        vb.addLayout(hb)

        self.grid = QGridLayout()
        self.grid.setSpacing(5)

        vb.addLayout(self.grid)
        w.setLayout(vb)
        self.setCentralWidget(w)

        self.init_map()
        self.update_status(STATUS_READY)

        self.reset_map()
        self.update_status(STATUS_READY)

        self.show()

    def init_map(self):
        #добавление позиции на карту
        for x in range(0, self.b_size):
            for y in range(0, self.b_size):
                w = Pos(x, y)
                self.grid.addWidget(w, y, x)
                w.clicked.connect(self.trigger_start)
                w.expandable.connect(self.expand_reveal)
                w.ohno.connect(self.game_over)

    #очистка карты
    def reset_map(self):
        for x in range(0, self.b_size):
            for y in range(0, self.b_size):
                w = self.grid.itemAtPosition(y, x).widget()
                w.reset()
        #добавление мин
        positions = []
        while len(positions) < self.n_mines:
            x, y = random.randint(0, self.b_size - 1), random.randint(0, self.b_size - 1)
            if (x, y) not in positions:
                w = self.grid.itemAtPosition(y, x).widget()
                w.is_mine = True
                positions.append((x, y))
        
        def get_adjacency_n(x, y):
            positions = self.get_surrounding(x, y)
            n_mines = sum(1 if w.is_mine else 0 for w in positions)

            return n_mines

        for x in range(0, self.b_size):
            for y in range(0, self.b_size):
                w = self.grid.itemAtPosition(y, x).widget()
                w.adjacent_n = get_adjacency_n(x, y)

        while True:
            x, y = random.randint(0, self.b_size - 1), random.randint(0, self.b_size - 1)
            w = self.grid.itemAtPosition(y, x).widget()
            # Начало игры не на мине
            if (x, y) not in positions:
                w = self.grid.itemAtPosition(y, x).widget()
                w.is_start = True

                #Раскрытие позиций вокруг указанной, кроме мин
                for w in self.get_surrounding(x, y):
                    if not w.is_mine:
                        w.click()
                break

    def get_surrounding(self, x, y):
        positions = []

        for xi in range(max(0, x - 1), min(x + 2, self.b_size)):
            for yi in range(max(0, y - 1), min(y + 2, self.b_size)):
                positions.append(self.grid.itemAtPosition(yi, xi).widget())

        return positions

    #нажатие кнопки
    def button_pressed(self):
        if self.status == STATUS_PLAYING:
            self.update_status(STATUS_FAILED)
            self.reveal_map()

        elif self.status == STATUS_FAILED:
            self.update_status(STATUS_READY)
            self.reset_map()

    #раскрытие карты
    def reveal_map(self):
        for x in range(0, self.b_size):
            for y in range(0, self.b_size):
                w = self.grid.itemAtPosition(y, x).widget()
                w.reveal()

    #раскрытие карты при нажатии на позицию
    def expand_reveal(self, x, y):
        for xi in range(max(0, x - 1), min(x + 2, self.b_size)):
            for yi in range(max(0, y - 1), min(y + 2, self.b_size)):
                w = self.grid.itemAtPosition(yi, xi).widget()
                if not w.is_mine:
                    w.click()

    def trigger_start(self, *args):
        if self.status != STATUS_PLAYING:

            #Начало игры
            self.update_status(STATUS_PLAYING)

            #При игры стартует таймер
            self._timer_start_nsecs = int(time.time())

    #обновление статуса игры
    def update_status(self, status):
        self.status = status
        self.button.setIcon(QIcon(STATUS_ICONS[self.status]))

    #секундомер
    def update_timer(self):
        if self.status == STATUS_PLAYING:
            n_secs = int(time.time()) - self._timer_start_nsecs
            self.clock.setText("%03d" % n_secs)

    #функция конца игры
    def game_over(self):
        self.reveal_map()
        self.update_status(STATUS_FAILED)

    #главное окно
    if __name__ == '__main__':
        app = QApplication([])
        window = MainWindow()
        apr.exec_()
